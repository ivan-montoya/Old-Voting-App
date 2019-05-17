package Driver;

public class VotingThread
{
	private String title;
	private String description;
	private String[] candidates;
	private int[] votes;
	private int numCandidates;
	
	public VotingThread(String title, String description, String[] candidates, int[] votes, int numCandidates)
	{
		setTitle(title);
		setDescription(description);
		setCandidates(candidates);
		setVotes(votes);
		setNumCandidates(numCandidates);
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String[] getCandidates()
	{
		return candidates;
	}
	
	public int[] getVotes()
	{
		return votes;
	}
	
	public int getNumCandidates()
	{
		return numCandidates;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setCandidates(String[] candidates)
	{
		this.candidates = candidates;
	}
	
	public void setVotes(int[] votes)
	{
		for (int i = 0; i < numCandidates; i++)
		{
			this.votes[i] = votes[i];
		}
	}
	
	public void setNumCandidates(int numCandidates)
	{
		this.numCandidates = numCandidates;
	}
	
	public void displayTitle(String title)
	{
		
	}
	
	public void displayDescription(String description)
	{
		
	}
	
	public void displayCandidates(String candidates[])
	{
		
	}
	
	public void vote(String candidate, int votes)
	{
		int i = 0;
				
		for (; i < numCandidates && candidates[i] != candidate; i++)
		{}
		
		if (i >= numCandidates)  // cannot find the candidate's name
		{
			// Error message
		}
		else  // find the candidate's name
		{
			this.votes[i] += votes;  // add votes to this candidate
		}
	}
}
