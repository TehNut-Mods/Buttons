package tehnut.buttons.api.button.utility;

/**
 * Provides information about the currently displayed button list.
 */
public interface IButtonListOverlay {
	/**
	 * Getter for the amount of columns being drawn.
	 *
	 * @return the amount of columns being drawn.
	 */
	int getColumns();

	/**
	 * @return if the button list is currently being drawn.
	 */
	boolean isOpen();
}
