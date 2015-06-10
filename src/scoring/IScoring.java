package scoring;

import token.IToken;

public interface IScoring {
	double getScore(IToken tk1, IToken tk2);
	double getGapScore();
}
