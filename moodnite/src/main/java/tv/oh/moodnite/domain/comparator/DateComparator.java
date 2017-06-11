package tv.oh.moodnite.domain.comparator;

import java.util.Comparator;

import tv.oh.moodnite.domain.Publication;

public class DateComparator implements Comparator<Publication> {

	@Override
	public int compare(Publication o1, Publication o2) {
        return o2.getDate().compareTo(o1.getDate());
	}
}
