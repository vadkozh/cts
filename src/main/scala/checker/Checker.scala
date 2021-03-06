package checker

import java.util.concurrent.{ExecutorService, Executors}

import com.twitter.util.{ExecutorServiceFuturePool, Future}
import db.{Question, TestTry}


trait Checker {

  protected val executorService: ExecutorService = Executors.newCachedThreadPool()

  protected val futurePool: ExecutorServiceFuturePool = new ExecutorServiceFuturePool(executorService)

  def check(testTry: TestTry): Future[Map[Question, Boolean]]
}
