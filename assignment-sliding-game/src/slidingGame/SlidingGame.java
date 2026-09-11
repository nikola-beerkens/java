package slidingGame;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A template of a sliding game
 */
public class SlidingGame implements Configuration {

	public static final int N = 3, SIZE = N * N, HOLE = SIZE;
	/**
	 * The board is represented by a 2-dimensional array; the position of the hole
	 * is kept in 2 variables holeX and holeY
	 */
	private final int[][] board =new int[N][N];
	private int holeX, holeY;
	private int manhattanDist = 1337;
	private SlidingGame parent = null;


	/**
	 * A constructor that initializes the board with the specified array
	 *
	 * @param start: a one dimensional array containing the initial board. The
	 *               elements of start are stored row-wise.
	 */
	public SlidingGame(int[] start) {
		assert start.length == N * N : "Length of specified board incorrect";

		for (int p = 0; p < start.length; p++) {
			board[p % N][p / N] = start[p];
			if (start[p] == HOLE) {
				holeX = p % N;
				holeY = p / N;
			}
		}

        setManhattanDistance();
	}
        
        public SlidingGame(SlidingGame old , Direction dir) {
            holeX = old.holeX + dir.getDX();
            holeY = old.holeY + dir.getDY();
            for (int i = 0; i < N; i++) {
                board[i] = Arrays.copyOf(old.board[i], N);
            }
            board[old.holeX][old.holeY] = board[holeX][holeY];
            board[holeX][holeY] = HOLE;
            parent = old;

            setManhattanDistance();
        }

	public int getManhattanDistance() {
		return manhattanDist;
	}

	public final void setManhattanDistance() {
            manhattanDist = 0;
            int counter = 1;
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    int tile = board[j][i];
                    if (tile != HOLE && tile != counter){
                        int col = (tile - 1) % N;
                        int row = (tile - 1) / N;
                        manhattanDist += Math.abs(i - row) + Math.abs(j - col);
                    }
                    counter++;
                }
            }
        }

	/**
	 * Converts a board into a printable representation. The hole is displayed as a
	 * space
	 *
	 * @return the string representation
	 */
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int puzzel = board[col][row];
				buf.append(puzzel == HOLE ? "  " : puzzel + " ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	@Override
	public boolean equals(Object o) {
            if (!(o instanceof SlidingGame)) {
                return false;
            }
            SlidingGame that = (SlidingGame) o;
            if (this.holeX != that.holeX) {
                return false;
            }
            if (this.holeY != that.holeY) {
                return false;
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (this.board[i][j] != that.board[i][j]) {
                        return false;
                    }
                }
            }
            return true;
	}

    @Override
    public int hashCode() {
        return -29791 * Arrays.deepHashCode(board) - 446126782;
    }

	@Override
	public boolean isSolution() {
        int expected = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[col][row] != expected++) {
                    return false;
                }
            }
        }
        return true;
	}
        
        public boolean canMove(Direction dir) {
        // indicates whether moving the horse in direction dir is a legal move
        switch (dir) {
            case NORTH:
                if (holeY != 0) {
                    return true;
                }
                break;
            case SOUTH:
                if (holeY != board.length - 1) {
                    return true;
                }
                break;
            case WEST:
                if (holeX != 0) {
                    return true;
                }
                break;
            case EAST:
                if (holeX != board.length - 1) {
                    return true;
                }
                break;
        }
        return false;
    }

	@Override
	public Collection<Configuration> successors() {
            Collection<Configuration> successors = new ArrayList<>();
            for (Direction dir : Direction.values()){
                if (canMove(dir)) {
                    successors.add(new SlidingGame(this, dir));
                }
            }
            return successors;
	}

	@Override
	public int compareTo(Configuration g) {
		if (g instanceof SlidingGame){
                return Integer.compare(manhattanDist, ((SlidingGame) g).manhattanDist);
            }
            return 0;
	}

	@Override
	public Configuration getParent() {
		return parent;
	}

}
