import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class iterates through a directory with a filter.
 */
public class FilteredFileIterator implements Iterator<File> {

	private DeepFileIterator fileIterator;
	private String searchPattern;
	private File nextMatchingFile;

	/**
	 * Creates a new FilteredFileIterator that initializes all fields and creates a
	 * new DeepFileIterator.
	 * 
	 * @param file          - location of directory
	 * @param searchPattern - how to find the file (.java, etc.)
	 * @throws FileNotFoundException
	 */
	public FilteredFileIterator(File file, String searchPattern) throws FileNotFoundException {
		if (file.exists() && file.isDirectory()) {
			this.searchPattern = searchPattern;
			try {
				fileIterator = new DeepFileIterator(file);
			} catch (FileNotFoundException e) {
			}
			nextMatchingFile = getNext();
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
		return nextMatchingFile != null;
	}

	@Override
	/**
	 * Gets the next file in the directory.
	 * 
	 * @return the next file in the directory.
	 */
	public File next() {
		if (hasNext()) {
			File temp = nextMatchingFile;
			nextMatchingFile = getNext();
			return temp;
		} else
			throw new NoSuchElementException("No file found.");
	}

	/**
	 * Helper method to get the next file.
	 * 
	 * @return the next file in the directory.
	 */
	private File getNext() {
		while (fileIterator.hasNext()) {
			File next = fileIterator.next();
			if (next.getName().contains(searchPattern)) {
				return next;
			}
		}
		return null;
	}

}
