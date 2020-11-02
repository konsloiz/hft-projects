package de.stuttgart.hft.bookapp.domain.model;

public class Genre{

	private int id;
	private String genreName;
	private boolean enabled;

	// Required by JSON
	public Genre() {}

	public Genre(int id, String genreName, boolean enabled) {
		super();
		this.id = id;
		this.genreName = genreName;
		this.enabled = enabled;
	}

	// Required for Mapper

	public int getId() {
		return id;
	}

	public String getGenreName() {
		return genreName;
	}

//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}

	@Override
	public String toString() {
		if(enabled)
			return genreName;
		else
			return "<html><font color = gray><i>" + genreName + "</i></font></html>";
	}
}
