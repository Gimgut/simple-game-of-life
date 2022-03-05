import java.util.List;
import java.util.Map;

public class Shapes {

    public static final List<Point> EMPTY = List.of();

    public static final List<Point> PERIOD_TWO_STICK = List.of(
            new Point(20,20),
            new Point(21,20),
            new Point(22,20)
    );

    public static final List<Point> PERIOD_THREE_PULSAR = List.of(

            // Top left
            new Point(20,20),
            new Point(19,20),
            new Point(18,20),

            new Point(16,19),
            new Point(16,18),
            new Point(16,17),

            new Point(18,15),
            new Point(19,15),
            new Point(20,15),

            new Point(21,19),
            new Point(21,18),
            new Point(21,17),

            // Top right
            new Point(20,22),
            new Point(19,22),
            new Point(18,22),

            new Point(21,23),
            new Point(21,24),
            new Point(21,25),

            new Point(20,27),
            new Point(19,27),
            new Point(18,27),

            new Point(16,25),
            new Point(16,24),
            new Point(16,23),

            // Bot left
            new Point(24,20),
            new Point(25,20),
            new Point(26,20),

            new Point(23,19),
            new Point(23,18),
            new Point(23,17),

            new Point(24,15),
            new Point(25,15),
            new Point(26,15),

            new Point(28,17),
            new Point(28,18),
            new Point(28,19),

            // Bot right
            new Point(24,22),
            new Point(25,22),
            new Point(26,22),

            new Point(28,23),
            new Point(28,24),
            new Point(28,25),

            new Point(26,27),
            new Point(25,27),
            new Point(24,27),

            new Point(23,25),
            new Point(23,24),
            new Point(23,23)
    );




    public static final List<Point> TENDS_TO_STATIONARY_PULSAR_WITH_ERROR = List.of(

            // Top left
            new Point(20,20),
            new Point(19,20),
            new Point(18,20),

            new Point(16,19),
            new Point(16,18),
            new Point(16,17),

            new Point(18,15),
            new Point(19,15),
            new Point(20,15),

            new Point(21,19),
            new Point(21,18),
            new Point(22,17),

            // Top right
            new Point(20,22),
            new Point(19,22),
            new Point(18,22),

            new Point(21,23),
            new Point(21,24),
            new Point(21,25),

            new Point(19,27),
            new Point(18,27),
            new Point(17,27),

            new Point(15,25),
            new Point(15,24),
            new Point(15,23),

            // Bot left
            new Point(24,20),
            new Point(25,20),
            new Point(26,20),

            new Point(23,19),
            new Point(23,18),
            new Point(23,17),

            new Point(24,15),
            new Point(25,15),
            new Point(26,15),

            new Point(28,17),
            new Point(28,18),
            new Point(28,19),

            // Bot right
            new Point(24,22),
            new Point(25,22),
            new Point(26,22),

            new Point(28,23),
            new Point(28,24),
            new Point(28,25),

            new Point(26,27),
            new Point(25,27),
            new Point(24,27),

            new Point(23,25),
            new Point(23,24),
            new Point(23,23)
    );

    public static final List<Point> GOSPER_GLIDER_GUN = List.of(
            new Point(15,3),
            new Point(16,3),
            new Point(15,4),
            new Point(16,4),

            new Point(15,7),
            new Point(15,8),
            new Point(15,10),
            new Point(14,8),
            new Point(16,8),
            new Point(17,9),
            new Point(13,9),

            new Point(12,11),
            new Point(12,12),
            new Point(13,13),
            new Point(14,14),
            new Point(15,14),
            new Point(16,14),
            new Point(17,13),
            new Point(18,12),
            new Point(18,11),

            new Point(17,19),

            new Point(17,22),
            new Point(17,23),
            new Point(17,24),
            new Point(16,24),
            new Point(15,23),

            new Point(15,28),
            new Point(16,28),

            new Point(15,30),
            new Point(14,31),
            new Point(14,32),
            new Point(13,31),
            new Point(13,32),
            new Point(12,31),
            new Point(12,32),
            new Point(11,30),

            new Point(11,28),
            new Point(10,28),

            new Point(14,37),
            new Point(14,38),
            new Point(13,37),
            new Point(13,38)
    );

    public static final Map<String, List<Point>> shapesDictionary = Map.of(
            "Pulsar", PERIOD_THREE_PULSAR,
            "Gosper gun", GOSPER_GLIDER_GUN
    );
}
