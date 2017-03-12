package tv.oh.moodnite.service.storage;

import org.springframework.context.annotation.Configuration;

@Configuration("storage")
public class StorageProperties {
	
	// Folder location for storing files.
	private String location = "C:/Users/casas/Pictures/Moodnite";

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
