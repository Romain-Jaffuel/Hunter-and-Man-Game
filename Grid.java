    package classique;

    public class Grid {
        private int width = 8;
        private int height = 8;
        private Cell[][] cells;
        private Player player;
        private Player hunter;


        public Grid() {
            cells = new Cell[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    cells[x][y] = new Cell(x, y);
                }
            }
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public Player getPlayer() {
            return player;
        }

        public void setPlayer(Player player) {
            this.player = player;
        }

        public Player getHunter() {
            return hunter;
        }

        public void setHunter(Player hunter) {
            this.hunter = hunter;
        }

        public boolean isInside(int x, int y) {
            // TODO implement here
            return false;
        }

        public Cell getCell(int x, int y) {
            return cells[x][y];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (cells[x][y].getOccupiedBy() instanceof Hunter) {
                        sb.append('H');
                    } else if (cells[x][y].getOccupiedBy() != null) {
                        sb.append('P');
                    } else if (cells[x][y].hasItem()) {
                        sb.append('I');
                    } else {
                        sb.append('.');
                    }
                    sb.append(" ");     // ajoute une string
                }
                sb.append('\n');    // saute une ligne
            }
            return sb.toString();
        }
    }