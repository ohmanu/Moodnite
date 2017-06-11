package tv.oh.moodnite.service.recomendation.comparator;

import java.util.Comparator;

import tv.oh.moodnite.service.recomendation.RecomendationCandidate;

public class ScoreComparator implements Comparator<RecomendationCandidate>{

	@Override
	public int compare(RecomendationCandidate o1, RecomendationCandidate o2) {
		return Double.compare(o2.getScore(), o1.getScore());
	}

}
