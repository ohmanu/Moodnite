package tv.oh.moodnite.domain.comparator;

import java.util.Comparator;

import tv.oh.moodnite.domain.Watched;

public class TitleComparator implements Comparator<Watched> {

	@Override
	public int compare(Watched o1, Watched o2) {
        return o1.getMovie().getTitle().compareTo(o2.getMovie().getTitle());
	}
}
