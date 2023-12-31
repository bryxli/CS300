import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Driver for RideQueue. Reads commands from a text files and executes them
 * accordingly.
 * 
 * @author Michelle Jensen
 */
public class ThemeParkApp {
	public static void main(String[] args) throws IOException {
		List<String> fileLines = Files.readAllLines(Paths.get("sample.txt"));
		String command = "";
		String[] commandParts;

		// Default queue capacity and ride capacity. Can change values if desired.
		RideQueue coaster = new RideQueue(8);
		int trainCapacity = 24;

		// Process each line in the text file.
		for (int i = 0; i < fileLines.size(); i++) {
			commandParts = fileLines.get(i).split(" ");
			command = commandParts[0].toUpperCase();

			// ENTER Command
			if (command.equals("E")) {
				enter(coaster, commandParts);
			}

			// BREAKDOWN Command
			if (command.equals("B")) {
				breakdown(coaster);
			}

			// PREVIEW Command
			if (command.equals("P")) {
				preview(coaster);
			}

			// RIDE Command
			if (command.equals("R")) {
				ride(coaster, trainCapacity);
			}

			// STATUS Command
			if (command.equals("S")) {
				status(coaster);
			}
		}
	}

	private static void status(RideQueue coaster) {
		System.out.println("Retrieving Status...");
		System.out.println(coaster.toString());
		System.out.println("------------------------------------");
	}

	private static void enter(RideQueue coaster, String[] commandParts) {
		System.out.println("Entering into ride line...");
		String groupName = commandParts[1];
		int groupSize = Integer.parseInt(commandParts[2]);

		BoardingGroup newGroup = new BoardingGroup(groupName, groupSize);

		if (commandParts.length == 4) {
			if (commandParts[3].toUpperCase().equals("V")) {
				newGroup.setVIP();
			}
		}

		try {
			coaster.enqueue(newGroup);
			System.out.println(groupName + "'s group of " + groupSize + " has entered the line for Badger Coaster.");
		} catch (IllegalStateException e) {
			System.out.println("Cannot fit group of that size into queue.");
		}

		System.out.println("------------------------------------");
	}

	private static void breakdown(RideQueue coaster) {
		System.out.println("Ride Breakdown...");
		System.out.println(
				"The ride has broken down. All " + coaster.size() + " group(s) have been removed from the line.");
		coaster.clear();
		System.out.println("------------------------------------");
	}

	private static void preview(RideQueue coaster) {
		System.out.println("Previewing the front of the line...");

		try {
			BoardingGroup peeked = coaster.peek();
			int peekedSize = peeked.getNumber();
			String peekedName = peeked.getName();
			System.out.println(peekedName + "'s group of " + peekedSize + " is at the front of the line.");
		} catch (NoSuchElementException e) {
			System.out.println("Cannot look at a group from an empty queue.");
		}

		System.out.println("------------------------------------");
	}

	private static void ride(RideQueue coaster, int trainCapacity) {
		System.out.println("Boarding and Running the Ride...");
		int ridingTrain = 0;

		while (!coaster.isEmpty()) {
			BoardingGroup peeked = coaster.peek();
			int peekedSize = peeked.getNumber();

			if (ridingTrain + peekedSize > trainCapacity) {
				break;
			}

			try {
				BoardingGroup removed = coaster.dequeue();
				String removedName = removed.getName();
				int removedSize = removed.getNumber();

				System.out
						.println(removedName + "'s group of " + removedSize + " has boarded the Badger Coaster train.");

				ridingTrain += removedSize;
			} catch (NoSuchElementException e) {
				System.out.println("Cannot remove a group from an empty queue.");
			}
		}

		if (ridingTrain == 0) {
			System.out.println("There is no one on the train to ride.");
		} else {
			System.out.println("Train of " + ridingTrain + " people has left the ride station.");
		}

		System.out.println("------------------------------------");
	}
}
