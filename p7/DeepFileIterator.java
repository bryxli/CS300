import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class deeply iterates through a directory.
 */
public class DeepFileIterator implements Iterator<File> {

	private File[] folderContents;
	private int nextIndex;
	DeepFileIterator contentsIterator;

	/**
	 * Creates a new DeepFileIterator that sorts the directory.
	 * 
	 * @param file - location of directory
	 * 
	 * @throws FileNotFoundException when file does not exist or is not a directory
	 */
	public DeepFileIterator(File file) throws FileNotFoundException {
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
		if (contentsIterator != null && contentsIterator.hasNext()) {
			return true;
		}
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
			if (contentsIterator != null && contentsIterator.hasNext()) {
				return contentsIterator.next();
			}
			if (folderContents[nextIndex].isDirectory()) {
				try {
					contentsIterator = new DeepFileIterator(folderContents[nextIndex]);
				} catch (FileNotFoundException e) {
				}
			}
			return folderContents[nextIndex++];
		} else
			throw new NoSuchElementException("No file found.");
	}

}
