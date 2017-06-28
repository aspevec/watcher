package watcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.watcher.model.ConfigFile;
import com.watcher.service.ConfigReaderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ConfigReaderTest {

	@Autowired
	private ConfigReaderService configReaderService;
	
	@Test
	public void testReadingConfiguration() {
		
		ConfigFile configFile = new ConfigFile();
		
		configFile.setFilename("log4j.properties");
		
		ConfigFile configFileProcessed = configReaderService.readFile(configFile, "project");
		
		assertNotNull(configFileProcessed);
		
		assertEquals(getExcpectedResult(), 
					 configFileProcessed.getContent());
		
	}
	
	private String getExcpectedResult() {
		
		String s = "<span style=\"color: #228B22;\"># Root logger option</span>"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.rootLogger</span>=INFO, file"
				+ "<br><br>"
				+ "<span style=\"color: #228B22;\"># Direct log messages to a log file</span>"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file</span>=org.apache.log4j.RollingFileAppender"
				+ "<br><br>"
				+ "<span style=\"color: #228B22;\">#Redirect to Tomcat logs folder</span>"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file.File</span>=${catalina.home}/logs/watcher.log"
				+ "<br><br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file.MaxFileSize</span>=10MB"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file.MaxBackupIndex</span>=10"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file.layout</span>=org.apache.log4j.PatternLayout"
				+ "<br>"
				+ "<span style=\"font-weight: bold;\">log4j.appender.file.layout.ConversionPattern</span>=%d{dd-MM-yyyy hh:mm:ss} %-5p %c{1}:%L - %m%n"
				+ "<br>";
		
		return s;
		
	}
	
}
