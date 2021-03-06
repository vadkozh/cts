package util

import com.typesafe.config.{ConfigException, ConfigFactory}

object Paths {

  protected val configuration = ConfigFactory.load().getConfig("paths")

  protected def config(name: String) =
    try {
      Some(configuration.getString(name))
    } catch {
      case e: ConfigException => None
    }

  implicit class PathExtension(val path: String) extends AnyVal {
    def wildcard(wildcardName: String = "*") = s"$path/:$wildcardName"
    def element(elementName: String) = s"$path/$elementName"
  }

  final val domain = config("domain.url") getOrElse "localhost:8888"

  final val signIn = config("auth.signIn") getOrElse "/signin"
  final val signUp = config("auth.signUp") getOrElse "/signup"
  final val signUpConfirmation = config("auth.signUpConfirm") getOrElse "/signupconfirm"
  final val signOut = config("auth.signOut") getOrElse "/signout"
  final val signUpConfirmInfo = config("auth.signUpConfirmInfo") getOrElse "/signupconfirminfo"

  final val profile = config("profile.index") getOrElse "/profile"
  final val profileQuestions = config("profile.questions") getOrElse "/profile/questions"
  final val profileQuestionCreate = config("profile.questions.create") getOrElse "/profile/questions/create"
  final val profileAnswers = config("profile.answers") getOrElse "/profile/answers"
  final val profileAnswersAdd = config("profile.answers.add") getOrElse "/profile/answers/add"

  final val profileQuestionGroups = config("profile.questionGroups") getOrElse "/profile/question-groups"
  final val profileQuestionGroupsCreate =
    config("profile.questionGroups.create") getOrElse "/profile/question-groups/create"

  final val userGroups = config("usergroups.index") getOrElse "/user-groups"
  final val userGroupsAll = config("usergroups.all") getOrElse "/user-groups/all"
  final val userGroupsMy = config("usergroups.my") getOrElse "/user-groups/my"
  final val userGroupsCreate = config("usergroups.create") getOrElse "/user-groups/create"
  final val userGroupsAcceptInvite = config("usergroups.invite.accept") getOrElse "/user-groups/invite-accept"
  final val userGroupsInviteSend = config("usergroups.invite.send") getOrElse "/user-groups/invite"
  final val userGroupsInviteApply = config("usergroups.invite.apply") getOrElse "/user-groups/apply-group"
  final val userGroupsInviteUser = config("usergroups.invite.senduser") getOrElse "/user-groups/invite-user"

  final val courses = config("courses.index") getOrElse "/courses"
  final val coursesAll = config("courses.all") getOrElse "/courses/all"
  final val coursesCreate = config("courses.create") getOrElse "/courses/create"
  final val coursesAddUserGroup = config("courses.usergroups.add") getOrElse "/courses/user-groups/add"
  final val coursesAddTest = config("courses.tests.add") getOrElse "/courses/tests/add"
}
