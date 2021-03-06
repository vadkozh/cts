package db

import scalikejdbc._

case class QuestionAnswer(question: Question, answer: String, creator: User)

object QuestionAnswer {
  def create(question: Question, user: User, answer: String) = {
    val result = using(DB(ConnectionPool.borrow())) { db =>
      db localTx { implicit session =>
        sql"""
             INSERT INTO question_answer(question_id, question_answer_value, question_answer_creator_id)
              VALUES (${question.id}, $answer, ${user.id})
           """
          .update()
          .apply()
      }
    }
    if (result > 0) Some(QuestionAnswer(question, answer, user)) else None
  }

  def fromQuestion(question: Question) = using(DB(ConnectionPool.borrow())) { db =>
    db readOnly { implicit session =>
      sql"""
           SELECT
            question_id,
            question_answer_value,
            question_answer_creator_id
           FROM
            question_answer
           WHERE question_id = ${question.id}
         """
        .map(x => fromResultSet(x, question))
        .list()
        .apply()
    }
  }

  def fromResultSet(x: WrappedResultSet, question: Question) = QuestionAnswer(
    question = question,
    answer = x.string("question_answer_value"),
    creator = User.findById(x.bigInt("question_answer_creator_id")).get
  )

  def fromResultSet(x: WrappedResultSet) = QuestionAnswer(
    question = Question.findById(x.bigInt("question_id")).get,
    answer = x.string("question_answer_value"),
    creator = User.findById(x.bigInt("question_answer_creator_id")).get
  )
}
