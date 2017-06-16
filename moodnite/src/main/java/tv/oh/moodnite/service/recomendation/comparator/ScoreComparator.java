package tv.oh.moodnite.service.recomendation.comparator;

import java.util.Comparator;

import tv.oh.moodnite.service.recommendation.RecommendationCandidate;

public class ScoreComparator implements Comparator<RecommendationCandidate>{

	@Override
	public int compare(RecommendationCandidate o1, RecommendationCandidate o2) {
		return Double.compare(o2.getScore(), o1.getScore());
	}

}
