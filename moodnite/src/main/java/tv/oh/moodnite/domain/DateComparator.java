package tv.oh.moodnite.domain;

import java.util.Comparator;

public class DateComparator implements Comparator<Watched> {

	@Override
	public int compare(Watched o1, Watched o2) {
        return o1.getDate().compareTo(o2.getDate());
	}
}
