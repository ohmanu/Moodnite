package tv.oh.moodnite.domain;

import java.util.Date;

public interface Publication {
	public Date getDate();
	public User getUser();
	public Movie getMovie();
	public String getText1();
	public String getText2();
}
