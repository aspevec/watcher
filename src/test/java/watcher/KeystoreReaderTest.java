package watcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.watcher.model.CertificateFile;
import com.watcher.model.KeyStoreFile;
import com.watcher.service.KeystoreReaderService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class KeystoreReaderTest {

	@Autowired
	private KeystoreReaderService keystoreReaderService;
	
	@Test
	public void testReadingKeystore() {
		
		KeyStoreFile keyStoreFile = new KeyStoreFile();
		
		keyStoreFile.setFilename("social.jks");
		
		keyStoreFile.setPassword("watcher123");
		
		KeyStoreFile keyStoreFileProcessed = keystoreReaderService.readFile(keyStoreFile, "project");
		
		assertNotNull(keyStoreFileProcessed);
		
		List<CertificateFile> list = keyStoreFileProcessed.getCertificates();
		
		assertEquals(2, list.size());
		
		assertEquals("facebook", list.get(0).getAlias());
		
		assertEquals("google", list.get(1).getAlias());
		
	}
	
}
