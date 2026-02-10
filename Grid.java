    package classique;

    public class Grid {
        private int width = 8;
        private int height = 8;
        private Cell[][] cells;
        private Player player;
        private Player hunter;
        private enum Direction {N, S, W, E}

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
            return x > -1 && x < width && y > -1 && y < height;
        }

        public Cell getCell(int x, int y) {
            return cells[x][y];
        }

        public <Direction> availableMoves(Player player) {
            Cell pos = player.getPosition();
            int posX = pos.getX();
            int posY = pos.getY();
        }

        public void move(Player player, Direction direction) {
            Cell oldCell = player.getPosition();
            Cell newCell = player.getPosition();
            oldCell.setOccupiedBy(null);
            newCell.setOccupiedBy(player);
            player.setPosition(newCell);
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