package watcher;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.watcher.model.LogFile;
import com.watcher.service.LogReaderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class LogReaderTest {

	@Autowired
	private LogReaderService logReaderService;
	
	@Test
	public void testReadingFile() {
		
		LogFile logFile = logReaderService.initializeLogFile();
		
		logFile.setFilename("watcher.log");
		
		LogFile logFileProcessed = logReaderService.readFile(logFile);
		
		assertNotNull(logFileProcessed);
		
		assertEquals(getExcpectedResult(), 
					 logFileProcessed.getContent());
		
	}
	
	@Test
	public void testReadingFileReversed() {
		
		LogFile logFile = logReaderService.initializeLogFile();
		
		logFile.setFilename("watcher.log");
		
		logFile.setNumberOfLastRows(62);
		
		LogFile logFileProcessed = logReaderService.readFile(logFile);
		
		assertNotNull(logFileProcessed);
		
		assertEquals(getExcpectedResultReversed(), 
					 logFileProcessed.getContent());
		
	}
	
	private String getExcpectedResult() {
		
		String s = "19-01-2017 09:15:26 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				 + "<br>19-01-2017 09:15:27 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Thu Jan 19 21:15:27 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:15:27 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				 + "<br>19-01-2017 09:15:27 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				 + "<br>19-01-2017 09:15:27 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:15:27 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:15:28 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:15:27 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:15:28 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1233 ms"
				 + "<br>19-01-2017 09:16:12 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				 + "<br>19-01-2017 09:16:12 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Thu Jan 19 21:16:12 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:16:12 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				 + "<br>19-01-2017 09:16:13 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher_1/WEB-INF/classes/configuration.properties]"
				 + "<br>19-01-2017 09:16:13 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:16:12 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:16:13 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:16:12 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:16:13 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 780 ms"
				 + "<br>19-01-2017 09:44:01 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				 + "<br>19-01-2017 09:44:01 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Thu Jan 19 21:44:01 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:44:01 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				 + "<br>19-01-2017 09:44:01 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				 + "<br>19-01-2017 09:44:02 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:44:01 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:44:02 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Thu Jan 19 21:44:01 CET 2017]; root of context hierarchy"
				 + "<br>19-01-2017 09:44:02 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1160 ms"
				 + "<br><span style=\"color: #FF0000;\">19-01-2017 09:44:20 ERROR KeystoreReaderServiceImpl:291 - java.io.IOException: Keystore was tampered with, or password was incorrect</span>"
				 + "<br>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> "
				 + "<span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:772)</span>"
				 + "</blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore$JKS.engineLoad(JavaKeyStore.java:55)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.security.KeyStore.load(KeyStore.java:1214)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.service.impl.KeystoreReaderServiceImpl.readKeyStore(KeystoreReaderServiceImpl.java:234)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.service.impl.KeystoreReaderServiceImpl.readFile(KeystoreReaderServiceImpl.java:194)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.controller.KeystoreController.readKeystoreFile(KeystoreController.java:65)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.lang.reflect.Method.invoke(Method.java:606)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.el.parser.AstValue.invoke(AstValue.java:279)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.el.MethodExpressionImpl.invoke(MethodExpressionImpl.java:273)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.event.MethodExpressionActionListener.processAction(MethodExpressionActionListener.java:153)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.event.ActionEvent.processListener(ActionEvent.java:88)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIComponentBase.broadcast(UIComponentBase.java:769)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UICommand.broadcast(UICommand.java:300)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIViewRoot.broadcastEvents(UIViewRoot.java:794)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIViewRoot.processApplication(UIViewRoot.java:1259)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.InvokeApplicationPhase.execute(InvokeApplicationPhase.java:81)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.Phase.doPhase(Phase.java:101)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:118)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.webapp.FacesServlet.service(FacesServlet.java:593)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:303)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:220)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:956)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:436)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1078)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:625)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:318)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.lang.Thread.run(Thread.java:745)</span></blockquote>"
				 + "<span style=\"color: #FF0000;\">Caused by: java.security.UnrecoverableKeyException: Password verification failed</span>"
				 + "<br>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:770)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	... 41 more</span></blockquote>"
				 + "<span style=\"color: #FF0000;\"></span>"
				 + "<br>"
				 + "<span style=\"color: #FF0000;\">19-01-2017 09:45:00 ERROR KeystoreReaderServiceImpl:291 - java.io.IOException: Keystore was tampered with, or password was incorrect</span>"
				 + "<br>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> "
				 + "<span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:772)</span>"
				 + "</blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore$JKS.engineLoad(JavaKeyStore.java:55)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.security.KeyStore.load(KeyStore.java:1214)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.service.impl.KeystoreReaderServiceImpl.readKeyStore(KeystoreReaderServiceImpl.java:234)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.service.impl.KeystoreReaderServiceImpl.readFile(KeystoreReaderServiceImpl.java:194)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.watcher.controller.KeystoreController.readKeystoreFile(KeystoreController.java:65)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.lang.reflect.Method.invoke(Method.java:606)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.el.parser.AstValue.invoke(AstValue.java:279)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.el.MethodExpressionImpl.invoke(MethodExpressionImpl.java:273)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.event.MethodExpressionActionListener.processAction(MethodExpressionActionListener.java:153)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.event.ActionEvent.processListener(ActionEvent.java:88)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIComponentBase.broadcast(UIComponentBase.java:769)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UICommand.broadcast(UICommand.java:300)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIViewRoot.broadcastEvents(UIViewRoot.java:794)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.component.UIViewRoot.processApplication(UIViewRoot.java:1259)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.InvokeApplicationPhase.execute(InvokeApplicationPhase.java:81)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.Phase.doPhase(Phase.java:101)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at com.sun.faces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:118)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at javax.faces.webapp.FacesServlet.service(FacesServlet.java:593)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:303)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:220)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:956)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:436)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1078)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:625)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:316)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	at java.lang.Thread.run(Thread.java:745)</span></blockquote>"
				 + "<span style=\"color: #FF0000;\">Caused by: java.security.UnrecoverableKeyException: Password verification failed</span>"
				 + "<br>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> "
				 + "<span style=\"color: #FF0000;\">	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:770)</span></blockquote>"
				 + "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> <span style=\"color: #FF0000;\">	... 41 more</span></blockquote>"
				 + "<span style=\"color: #FF0000;\"></span>"
				 + "<br>22-01-2017 02:13:21 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				 + "<br>22-01-2017 02:13:21 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				 + "<br>22-01-2017 02:13:21 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				 + "<br>22-01-2017 02:13:21 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				 + "<br>22-01-2017 02:13:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				 + "<br>22-01-2017 02:13:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				 + "<br>22-01-2017 02:13:22 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1365 ms"
				 + "<br>30-01-2017 08:02:21 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				 + "<br>30-01-2017 08:02:21 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				 + "<br>30-01-2017 08:02:21 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				 + "<br>30-01-2017 08:02:21 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				 + "<br>30-01-2017 08:02:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				 + "<br>30-01-2017 08:02:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				 + "<br>30-01-2017 08:02:22 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1306 ms"
				 + "<br>";
		
		return s;
		
	}
	
	private String getExcpectedResultReversed() {
		
		String s = "<br>"
				+ "19-01-2017 09:45:00 ERROR KeystoreReaderServiceImpl:291 - java.io.IOException: Keystore was tampered with, or password was incorrect<br>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:772)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.security.provider.JavaKeyStore$JKS.engineLoad(JavaKeyStore.java:55)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at java.security.KeyStore.load(KeyStore.java:1214)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.watcher.service.impl.KeystoreReaderServiceImpl.readKeyStore(KeystoreReaderServiceImpl.java:234)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.watcher.service.impl.KeystoreReaderServiceImpl.readFile(KeystoreReaderServiceImpl.java:194)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.watcher.controller.KeystoreController.readKeystoreFile(KeystoreController.java:65)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at java.lang.reflect.Method.invoke(Method.java:606)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.el.parser.AstValue.invoke(AstValue.java:279)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.el.MethodExpressionImpl.invoke(MethodExpressionImpl.java:273)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.event.MethodExpressionActionListener.processAction(MethodExpressionActionListener.java:153)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.event.ActionEvent.processListener(ActionEvent.java:88)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.component.UIComponentBase.broadcast(UIComponentBase.java:769)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.component.UICommand.broadcast(UICommand.java:300)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.component.UIViewRoot.broadcastEvents(UIViewRoot.java:794)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.component.UIViewRoot.processApplication(UIViewRoot.java:1259)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.sun.faces.lifecycle.InvokeApplicationPhase.execute(InvokeApplicationPhase.java:81)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.sun.faces.lifecycle.Phase.doPhase(Phase.java:101)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at com.sun.faces.lifecycle.LifecycleImpl.execute(LifecycleImpl.java:118)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at javax.faces.webapp.FacesServlet.service(FacesServlet.java:593)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:303)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:241)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:208)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:220)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:122)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:169)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:956)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:116)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:436)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1078)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:625)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:316)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at java.lang.Thread.run(Thread.java:745)</blockquote>"
				+ "Caused by: java.security.UnrecoverableKeyException: Password verification failed"
				+ "<br>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	at sun.security.provider.JavaKeyStore.engineLoad(JavaKeyStore.java:770)</blockquote>"
				+ "<blockquote style=\"margin: 0 0 0 40px; border: none; padding: 0px;\"> 	... 41 more</blockquote>"
				+ "<br>22-01-2017 02:13:21 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				+ "<br>22-01-2017 02:13:21 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				+ "<br>22-01-2017 02:13:21 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				+ "<br>22-01-2017 02:13:21 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				+ "<br>22-01-2017 02:13:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				+ "<br>22-01-2017 02:13:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Sun Jan 22 14:13:21 CET 2017]; root of context hierarchy"
				+ "<br>22-01-2017 02:13:22 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1365 ms"
				+ "<br>30-01-2017 08:02:21 INFO  ContextLoader:285 - Root WebApplicationContext: initialization started"
				+ "<br>30-01-2017 08:02:21 INFO  XmlWebApplicationContext:510 - Refreshing Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				+ "<br>30-01-2017 08:02:21 INFO  XmlBeanDefinitionReader:317 - Loading XML bean definitions from ServletContext resource [/WEB-INF/applicationContext.xml]"
				+ "<br>30-01-2017 08:02:21 INFO  PropertyPlaceholderConfigurer:172 - Loading properties file from URL [file:/D:/apache-tomcat-7.0.69/webapps/watcher/WEB-INF/classes/configuration.properties]"
				+ "<br>30-01-2017 08:02:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				+ "<br>30-01-2017 08:02:22 INFO  RequestMappingHandlerAdapter:517 - Looking for @ControllerAdvice: Root WebApplicationContext: startup date [Mon Jan 30 20:02:21 CET 2017]; root of context hierarchy"
				+ "<br>30-01-2017 08:02:22 INFO  ContextLoader:325 - Root WebApplicationContext: initialization completed in 1306 ms"
				+ "<br>";
		
		return s;
		
	}
	
}
