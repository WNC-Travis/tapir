package sttp.tapir.server.vertx

import sttp.client3.testing.SttpBackendStub
import sttp.monad.FutureMonad
import sttp.tapir.server.interceptor.CustomiseInterceptors
import sttp.tapir.server.tests.{CreateServerStubTest, ServerStubTest}

import scala.concurrent.{ExecutionContext, Future}

object VertxFutureCreateServerStubTest extends CreateServerStubTest[Future, VertxFutureServerOptions] {
  override def customiseInterceptors: CustomiseInterceptors[Future, VertxFutureServerOptions] =
    VertxFutureServerOptions.customiseInterceptors
  override def stub[R]: SttpBackendStub[Future, R] = SttpBackendStub(new FutureMonad()(ExecutionContext.global))
  override def asFuture[A]: Future[A] => Future[A] = identity
}

class VertxFutureServerStubTest extends ServerStubTest(VertxFutureCreateServerStubTest)
