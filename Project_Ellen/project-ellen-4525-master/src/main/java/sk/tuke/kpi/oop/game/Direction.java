package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTH(0,1),
    SOUTH(0,-1),
    EAST(1,0),
    WEST(-1,0),
    NONE(0 ,0),
    NORTHEAST(1,1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1);

    private int dx;
    private int dy;

     Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public float getAngle() {
        //return dx > 0 && dy == 0 ? 90 : 0;
        if (dx == 0) {
            if (dy == 1) {
                return 0;
            } else {
                return 180;
            }
        }
        else if (dx == 1) {
            if (dy == 0) {
                return 270;
            }
            else if (dy == 1) {
                return 315;
            }
            else {
                return 225;
            }
        }
        else {
            if (dy == 0) {
                return  90;
            }
            else if (dy == 1) {
                return 45;
            }
            else {
                return 135;
            }
        }
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Direction combine(Direction other) {
         int newDx;
         int newDy;

         if (this == other) {
             return this;
         }
         if (other.getDx() == getDx()) {
             newDx = getDx();
         } else {
             newDx = other.getDx() + getDx();
         }
        if (other.getDy() == getDy()) {
            newDy= getDy();
        } else {
            newDy = other.getDy() + getDy();
        }
        Direction direction = NONE;
        for (Direction new_direct : Direction.values()) {
            if (new_direct.getDx() == newDx && new_direct.getDy() == newDy) {
                direction = new_direct;
            }
        }

        return direction;
    }

    public static Direction fromAngle(float angle) {
         if (angle == 0) {
             return NORTH;
         }
         else if (angle == 45) {
             return NORTHWEST;
         }
         else if (angle == 90) {
             return WEST;
         }
         else if (angle == 135) {
             return SOUTHWEST;
         }
         else if (angle == 180) {
             return SOUTH;
         }
         else if (angle == 225) {
             return SOUTHEAST;
         }
         else if (angle == 270) {
             return EAST;
         } else {
             return NORTHEAST;
         }

    }
}
