package tv.oh.moodnite.domain;

import java.util.Comparator;

public class TitleComparator implements Comparator<Watched> {

	@Override
	public int compare(Watched o1, Watched o2) {
        return o1.getMovie().getTitle().compareTo(o2.getMovie().getTitle());
	}
}
