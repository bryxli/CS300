import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class shallowly iterates through a directory.
 */
public class ShallowFileIterator implements Iterator<File> {

	private File[] folderContents;
	private int nextIndex;

	/**
	 * Creates a new ShallowFilterIterator that sorts the directory.
	 * 
	 * @param file - location of directory
	 * 
	 * @throws FileNotFoundException when file does not exist or is not a directory
	 */
	public ShallowFileIterator(File file) throws FileNotFoundException {
		if (file.exists() && file.isDirectory()) {
			folderContents = file.listFiles();
			Arrays.sort(folderContents);
			nextIndex = 0;
		} else
			throw new FileNotFoundException("File not found or not a directory.");
	}

	@Override
	/**
	 * Checks to see if there is a file
	 * 
	 * @return true if the check works as intended, false otherwise.
	 */
	public boolean hasNext() {
		return nextIndex < folderContents.length;
	}

	@Override
	/**
	 * Gets the next file in the directory.
	 * 
	 * @return the next file in the directory.
	 */
	public File next() {
		if (hasNext()) {
			return folderContents[nextIndex++];
		} else
			throw new NoSuchElementException("No file found.");
	}

}
