package Driver;

public class RegisteredUser extends GeneralUser
{
	protected VotingThread[] threads;
	protected int numThreads;
	
	public RegisteredUser(String userName, String email, String password, String name, String birthday, VotingThread[] threads, int numThreads)
	{
		super(userName, email, password, name, birthday);
		setThreads(threads);
		setNumThreads(numThreads);
	}
	
	public VotingThread[] getThreads()
	{
		return threads;
	}
	
	public int getNumThreads()
	{
		return numThreads;
	}
	
	public void setThreads(VotingThread[] threads)
	{
		for (int i = 0; i < numThreads; i++)
		{
			this.threads[i] = threads[i];
		}
	}
	
	public void setNumThreads(int numThreads)
	{
		this.numThreads = numThreads;
	}
	
	public void logIn(String myEmail, String myPassword)
	{
		if(myEmail == email && myPassword == password)
		{
			// Loged in screen
		}
		else
		{
			// Error message
		}
	}
	
	public void createVotingThread(String threadName, String threadDescription, String[] candidateNames)
	{
		threads[numThreads].setTitle(threadName);
		threads[numThreads].setDescription(threadDescription);
		threads[numThreads].setCandidates(candidateNames);
		numThreads++;
	}
	
	public void viewVotingThread()
	{
		
	}
	
	public void vote(String threadTitle, String candidate, int votes)
	{
		int i = 0;
				
		for (; i < numThreads && threads[i].getTitle() != threadTitle; i++)
		{}
	
		if (i >= numThreads)  // cannot find the thread
		{
			// Error message
		}
		else  // find the thread
		{
			threads[i].vote(candidate, votes);  // vote the candidate
		}
	}
}
