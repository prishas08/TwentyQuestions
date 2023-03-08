import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	GameTreeNode root;
	String file;
	public GameTree(String fileName)
	{
		file = fileName;
		Scanner input = null;
		try {
			input = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*while(input.hasNext()==true)
		{
			System.out.println("IM HEREa");

			root = newGame(root, input.next());
		}*/
		root = newGame(root, input);
		System.out.println(toString());
		//TODO
	}
	private GameTreeNode newGame(GameTreeNode newRoot, Scanner input)
	{
		if(input==null)
		{
			newRoot = new GameTreeNode(input.nextLine());
		}
		//if(input!=null)
		{
			String current = input.nextLine();
			//the string is a question
			if(current.substring(current.length()-1, current.length()).equals("?"))
			{
				newRoot = new GameTreeNode(current);
			}
			System.out.println("HEHEHEH" + current);
			newRoot.left = newGame(newRoot.left, input);
			newRoot.right = newGame(newRoot.right, input);
		}
		
		return root;
	}
	
	private class GameTreeNode
	{
		String val;
		GameTreeNode left, right;
		
		public GameTreeNode(String val)
		{
			this.val = val;
			left = right = null;
		}
		
		@Override
		public String toString()
		{
			return "" + this.val;
		}
	}
	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		addQuestion(this.root, newQ);
		addAnswer(this.root, newA);
		//TODO
	}
	
	private void addQuestion(GameTreeNode root, String newQ)
	{
		String s = getCurrent();
		if(root==null)
		{
			root = new GameTreeNode(newQ);
		}
		if(s!=root.left.val)
		{
			addQuestion(root.left, newQ);
		}
		
	}
	private void addAnswer(GameTreeNode root, String newQ)
	{
		String s = getCurrent();
		if(root==null)
		{
			root = new GameTreeNode(newQ);
		}
		if(s!=root.left.val)
		{
			addAnswer(root.left, newQ);
		}
		
	}
	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		//TODO
		String current = getCurrent();
		if(current.substring(current.length()-1, current.length()).equals("?"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		
		return root.val; //replace
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if(yesOrNo.equals(Choice.Yes))
		{
			root = root.left;
		}
		else if(yesOrNo.equals(Choice.No))
		{
			root = root.right;
		}
		//TODO
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		GameTreeNode newRoot = null;
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root = newGame(newRoot, input);
		//TODO
	}

	@Override
	public String toString()
	{
		return toString(root, "");
	}
	
	private String toString(GameTreeNode newRoot, String s)
	{
		String str = "";
		if(newRoot!=null)
		{
		
			str += "- " + s  + " " + newRoot.val + "\n";
			str += toString(newRoot.left, s);
			str += toString(newRoot.right, s);

		}
		return str;
	}
	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		GameTreeNode newRoot = root;
		Scanner input = null;
		try {
			input = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		root = newGame(newRoot, input);
		//TODO
	}
}
