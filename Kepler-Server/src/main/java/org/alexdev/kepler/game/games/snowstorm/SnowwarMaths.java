package org.alexdev.kepler.game.games.snowstorm;

import org.alexdev.kepler.game.pathfinder.Position;

public class SnowwarMaths {
    public static int convertToGameCoordinate(int num) {
        int pAccuracyFactor = 100;
        int pTileSize = 32;
        int tMultiplier = pTileSize * pAccuracyFactor;
        return num / tMultiplier;
    }

    public static Integer[] m_table = new Integer[]{
            Integer.valueOf(0), Integer.valueOf(16), Integer.valueOf(22), Integer.valueOf(27), Integer.valueOf(32), Integer.valueOf(35), Integer.valueOf(39), Integer.valueOf(42), Integer.valueOf(45), Integer.valueOf(48),
            Integer.valueOf(50), Integer.valueOf(53), Integer.valueOf(55), Integer.valueOf(57), Integer.valueOf(59), Integer.valueOf(61), Integer.valueOf(64), Integer.valueOf(65), Integer.valueOf(67), Integer.valueOf(69),
            Integer.valueOf(71), Integer.valueOf(73), Integer.valueOf(75), Integer.valueOf(76), Integer.valueOf(78), Integer.valueOf(80), Integer.valueOf(81), Integer.valueOf(83), Integer.valueOf(84), Integer.valueOf(86),
            Integer.valueOf(87), Integer.valueOf(89), Integer.valueOf(90), Integer.valueOf(91), Integer.valueOf(93), Integer.valueOf(94), Integer.valueOf(96), Integer.valueOf(97), Integer.valueOf(98), Integer.valueOf(99),
            Integer.valueOf(101), Integer.valueOf(102), Integer.valueOf(103), Integer.valueOf(104), Integer.valueOf(106), Integer.valueOf(107), Integer.valueOf(108), Integer.valueOf(109), Integer.valueOf(110), Integer.valueOf(112),
            Integer.valueOf(113), Integer.valueOf(114), Integer.valueOf(115), Integer.valueOf(116), Integer.valueOf(117), Integer.valueOf(118), Integer.valueOf(119), Integer.valueOf(120), Integer.valueOf(121), Integer.valueOf(122),
            Integer.valueOf(123), Integer.valueOf(124), Integer.valueOf(125), Integer.valueOf(126), Integer.valueOf(128), Integer.valueOf(128), Integer.valueOf(129), Integer.valueOf(130), Integer.valueOf(131), Integer.valueOf(132),
            Integer.valueOf(133), Integer.valueOf(134), Integer.valueOf(135), Integer.valueOf(136), Integer.valueOf(137), Integer.valueOf(138), Integer.valueOf(139), Integer.valueOf(140), Integer.valueOf(141), Integer.valueOf(142),
            Integer.valueOf(143), Integer.valueOf(144), Integer.valueOf(144), Integer.valueOf(145), Integer.valueOf(146), Integer.valueOf(147), Integer.valueOf(148), Integer.valueOf(149), Integer.valueOf(150), Integer.valueOf(150),
            Integer.valueOf(151), Integer.valueOf(152), Integer.valueOf(153), Integer.valueOf(154), Integer.valueOf(155), Integer.valueOf(155), Integer.valueOf(156), Integer.valueOf(157), Integer.valueOf(158), Integer.valueOf(159),
            Integer.valueOf(160), Integer.valueOf(160), Integer.valueOf(161), Integer.valueOf(162), Integer.valueOf(163), Integer.valueOf(163), Integer.valueOf(164), Integer.valueOf(165), Integer.valueOf(166), Integer.valueOf(167),
            Integer.valueOf(167), Integer.valueOf(168), Integer.valueOf(169), Integer.valueOf(170), Integer.valueOf(170), Integer.valueOf(171), Integer.valueOf(172), Integer.valueOf(173), Integer.valueOf(173), Integer.valueOf(174),
            Integer.valueOf(175), Integer.valueOf(176), Integer.valueOf(176), Integer.valueOf(177), Integer.valueOf(178), Integer.valueOf(178), Integer.valueOf(179), Integer.valueOf(180), Integer.valueOf(181), Integer.valueOf(181),
            Integer.valueOf(182), Integer.valueOf(183), Integer.valueOf(183), Integer.valueOf(184), Integer.valueOf(185), Integer.valueOf(185), Integer.valueOf(186), Integer.valueOf(187), Integer.valueOf(187), Integer.valueOf(188),
            Integer.valueOf(189), Integer.valueOf(189), Integer.valueOf(190), Integer.valueOf(191), Integer.valueOf(192), Integer.valueOf(192), Integer.valueOf(193), Integer.valueOf(193), Integer.valueOf(194), Integer.valueOf(195),
            Integer.valueOf(195), Integer.valueOf(196), Integer.valueOf(197), Integer.valueOf(197), Integer.valueOf(198), Integer.valueOf(199), Integer.valueOf(199), Integer.valueOf(200), Integer.valueOf(201), Integer.valueOf(201),
            Integer.valueOf(202), Integer.valueOf(203), Integer.valueOf(203), Integer.valueOf(204), Integer.valueOf(204), Integer.valueOf(205), Integer.valueOf(206), Integer.valueOf(206), Integer.valueOf(207), Integer.valueOf(208),
            Integer.valueOf(208), Integer.valueOf(209), Integer.valueOf(209), Integer.valueOf(210), Integer.valueOf(211), Integer.valueOf(211), Integer.valueOf(212), Integer.valueOf(212), Integer.valueOf(213), Integer.valueOf(214),
            Integer.valueOf(214), Integer.valueOf(215), Integer.valueOf(215), Integer.valueOf(216), Integer.valueOf(217), Integer.valueOf(217), Integer.valueOf(218), Integer.valueOf(218), Integer.valueOf(219), Integer.valueOf(219),
            Integer.valueOf(220), Integer.valueOf(221), Integer.valueOf(221), Integer.valueOf(222), Integer.valueOf(222), Integer.valueOf(223), Integer.valueOf(224), Integer.valueOf(224), Integer.valueOf(225), Integer.valueOf(225),
            Integer.valueOf(226), Integer.valueOf(226), Integer.valueOf(227), Integer.valueOf(227), Integer.valueOf(228), Integer.valueOf(229), Integer.valueOf(229), Integer.valueOf(230), Integer.valueOf(230), Integer.valueOf(231),
            Integer.valueOf(231), Integer.valueOf(232), Integer.valueOf(232), Integer.valueOf(233), Integer.valueOf(234), Integer.valueOf(234), Integer.valueOf(235), Integer.valueOf(235), Integer.valueOf(236), Integer.valueOf(236),
            Integer.valueOf(237), Integer.valueOf(237), Integer.valueOf(238), Integer.valueOf(238), Integer.valueOf(239), Integer.valueOf(240), Integer.valueOf(240), Integer.valueOf(241), Integer.valueOf(241), Integer.valueOf(242),
            Integer.valueOf(242), Integer.valueOf(243), Integer.valueOf(243), Integer.valueOf(244), Integer.valueOf(244), Integer.valueOf(245), Integer.valueOf(245), Integer.valueOf(246), Integer.valueOf(246), Integer.valueOf(247),
            Integer.valueOf(247), Integer.valueOf(248), Integer.valueOf(248), Integer.valueOf(249), Integer.valueOf(249), Integer.valueOf(250), Integer.valueOf(250), Integer.valueOf(251), Integer.valueOf(251), Integer.valueOf(252),
            Integer.valueOf(252), Integer.valueOf(253), Integer.valueOf(253), Integer.valueOf(254), Integer.valueOf(254), Integer.valueOf(255)};

    public static int fast_sqrt(int x) {
        if (x >= 65536) {
            if (x >= 16777216) {
                if (x >= 268435456) {
                    if (x >= 1073741824)
                        return m_table[x / 16777216].intValue() * 256;
                    return m_table[x / 4194304].intValue() * 128;
                }
                if (x >= 67108864)
                    return m_table[x / 1048576].intValue() * 64;
                return m_table[x / 262144].intValue() * 32;
            }
            if (x >= 1048576) {
                if (x >= 4194304)
                    return m_table[x / 65536].intValue() * 16;
                return m_table[x / 16384].intValue() * 8;
            }
            if (x >= 262144)
                return m_table[x / 4096].intValue() * 4;
            return m_table[x / 1024].intValue() * 2;
        }
        if (x >= 256) {
            if (x >= 4096) {
                if (x >= 16384)
                    return m_table[x / 256].intValue();
                return m_table[x / 64].intValue() / 2;
            }
            if (x >= 1024)
                return m_table[x / 16].intValue() / 4;
            return m_table[x / 4].intValue() / 8;
        }
        if (x >= 0)
            return m_table[x].intValue() / 16;
        return -1;
    }

    public static Integer[] m_component = new Integer[]{
            Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(2),
            Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(4), Integer.valueOf(4),
            Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(6), Integer.valueOf(6), Integer.valueOf(6), Integer.valueOf(6),
            Integer.valueOf(7), Integer.valueOf(7), Integer.valueOf(7), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(8), Integer.valueOf(9),
            Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(10), Integer.valueOf(11), Integer.valueOf(11),
            Integer.valueOf(11), Integer.valueOf(11), Integer.valueOf(12), Integer.valueOf(12), Integer.valueOf(12), Integer.valueOf(12), Integer.valueOf(12), Integer.valueOf(13), Integer.valueOf(13), Integer.valueOf(13),
            Integer.valueOf(13), Integer.valueOf(13), Integer.valueOf(14), Integer.valueOf(14), Integer.valueOf(14), Integer.valueOf(14), Integer.valueOf(15), Integer.valueOf(15), Integer.valueOf(15), Integer.valueOf(15),
            Integer.valueOf(15), Integer.valueOf(16), Integer.valueOf(16), Integer.valueOf(16), Integer.valueOf(16), Integer.valueOf(16), Integer.valueOf(17), Integer.valueOf(17), Integer.valueOf(17), Integer.valueOf(17),
            Integer.valueOf(17), Integer.valueOf(18), Integer.valueOf(18), Integer.valueOf(18), Integer.valueOf(18), Integer.valueOf(18), Integer.valueOf(19), Integer.valueOf(19), Integer.valueOf(19), Integer.valueOf(19),
            Integer.valueOf(19), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(20), Integer.valueOf(21), Integer.valueOf(21), Integer.valueOf(21), Integer.valueOf(21),
            Integer.valueOf(21), Integer.valueOf(22), Integer.valueOf(22), Integer.valueOf(22), Integer.valueOf(22), Integer.valueOf(22), Integer.valueOf(23), Integer.valueOf(23), Integer.valueOf(23), Integer.valueOf(23),
            Integer.valueOf(23), Integer.valueOf(24), Integer.valueOf(24), Integer.valueOf(24), Integer.valueOf(24), Integer.valueOf(24), Integer.valueOf(24), Integer.valueOf(25), Integer.valueOf(25), Integer.valueOf(25),
            Integer.valueOf(25), Integer.valueOf(25), Integer.valueOf(26), Integer.valueOf(26), Integer.valueOf(26), Integer.valueOf(26), Integer.valueOf(26), Integer.valueOf(26), Integer.valueOf(27), Integer.valueOf(27),
            Integer.valueOf(27), Integer.valueOf(27), Integer.valueOf(27), Integer.valueOf(28), Integer.valueOf(28), Integer.valueOf(28), Integer.valueOf(28), Integer.valueOf(28), Integer.valueOf(28), Integer.valueOf(29),
            Integer.valueOf(29), Integer.valueOf(29), Integer.valueOf(29), Integer.valueOf(29), Integer.valueOf(29), Integer.valueOf(30), Integer.valueOf(30), Integer.valueOf(30), Integer.valueOf(30), Integer.valueOf(30),
            Integer.valueOf(30), Integer.valueOf(31), Integer.valueOf(31), Integer.valueOf(31), Integer.valueOf(31), Integer.valueOf(31), Integer.valueOf(31), Integer.valueOf(32), Integer.valueOf(32), Integer.valueOf(32),
            Integer.valueOf(32), Integer.valueOf(32), Integer.valueOf(32), Integer.valueOf(33), Integer.valueOf(33), Integer.valueOf(33), Integer.valueOf(33), Integer.valueOf(33), Integer.valueOf(33), Integer.valueOf(34),
            Integer.valueOf(34), Integer.valueOf(34), Integer.valueOf(34), Integer.valueOf(34), Integer.valueOf(34), Integer.valueOf(34), Integer.valueOf(35), Integer.valueOf(35), Integer.valueOf(35), Integer.valueOf(35),
            Integer.valueOf(35), Integer.valueOf(35), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(36), Integer.valueOf(37),
            Integer.valueOf(37), Integer.valueOf(37), Integer.valueOf(37), Integer.valueOf(37), Integer.valueOf(37), Integer.valueOf(37), Integer.valueOf(38), Integer.valueOf(38), Integer.valueOf(38), Integer.valueOf(38),
            Integer.valueOf(38), Integer.valueOf(38), Integer.valueOf(38), Integer.valueOf(39), Integer.valueOf(39), Integer.valueOf(39), Integer.valueOf(39), Integer.valueOf(39), Integer.valueOf(39), Integer.valueOf(39),
            Integer.valueOf(39), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(40), Integer.valueOf(41), Integer.valueOf(41),
            Integer.valueOf(41), Integer.valueOf(41), Integer.valueOf(41), Integer.valueOf(41), Integer.valueOf(41), Integer.valueOf(41), Integer.valueOf(42), Integer.valueOf(42), Integer.valueOf(42), Integer.valueOf(42),
            Integer.valueOf(42), Integer.valueOf(42), Integer.valueOf(42), Integer.valueOf(42), Integer.valueOf(43), Integer.valueOf(43), Integer.valueOf(43), Integer.valueOf(43), Integer.valueOf(43), Integer.valueOf(43),
            Integer.valueOf(43), Integer.valueOf(43), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44), Integer.valueOf(44),
            Integer.valueOf(44), Integer.valueOf(45), Integer.valueOf(45), Integer.valueOf(45), Integer.valueOf(45), Integer.valueOf(45)};

    public static int getAngleFromComponentsMaths(int tX, int tY) {
        if (Math.abs(tX) <= Math.abs(tY)) {
            if (tY == 0)
                tY = 1;
            tX *= 256;
            int i = tX / tY;
            if (i < 0)
                i = -i;
            if (i > 255)
                i = 255;
            if (tY < 0) {
                if (tX > 0)
                    return m_component[i].intValue();
                return 360 - m_component[i].intValue();
            }
            if (tX > 0)
                return 180 - m_component[i].intValue();
            return 180 + m_component[i].intValue();
        }
        if (tX == 0)
            tX = 1;
        tY *= 256;
        int temp = tY / tX;
        if (temp < 0)
            temp = -temp;
        if (temp > 255)
            temp = 255;
        if (tY < 0) {
            if (tX > 0)
                return 90 - m_component[temp].intValue();
            return 270 + m_component[temp].intValue();
        }
        if (tX > 0)
            return 90 + m_component[temp].intValue();
        return 270 - m_component[temp].intValue();
    }

    public static int direction360to8(int tValue) {
        return validateDirection8Value(validateDirection360Value(tValue - 22) / 45 + 1);
    }

    public static int rotateDirection45DegreesCW(int tValue) {
        return validateDirection360Value(tValue + 45);
    }

    public static int rotateDirection45DegreesCCW(int tValue) {
        return validateDirection360Value(tValue - 45);
    }

    public static int validateDirection8Value(int tValue) {
        if (tValue > 7) {
            tValue %= 8;
        } else if (tValue < 0) {
            tValue = (8 + tValue % 8) % 8;
        }
        return tValue;
    }

    public static int validateDirection360Value(int tValue) {
        if (tValue > 359) {
            tValue %= 360;
        } else if (tValue < 0) {
            tValue = 360 + tValue % 360;
        }
        return tValue;
    }

    public static int getAngleFromComponents(int tX, int tY) {
        int outPut = validateDirection360Value(getAngleFromComponentsMaths(tX, tY));
        return outPut;
    }

    public static Integer[] m_ar_ar_iTblX = new Integer[]{
            Integer.valueOf(0), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(13), Integer.valueOf(17), Integer.valueOf(22), Integer.valueOf(26), Integer.valueOf(31), Integer.valueOf(35), Integer.valueOf(40),
            Integer.valueOf(44), Integer.valueOf(48), Integer.valueOf(53), Integer.valueOf(57), Integer.valueOf(61), Integer.valueOf(66), Integer.valueOf(70), Integer.valueOf(74), Integer.valueOf(79), Integer.valueOf(83),
            Integer.valueOf(87), Integer.valueOf(91), Integer.valueOf(95), Integer.valueOf(100), Integer.valueOf(104), Integer.valueOf(108), Integer.valueOf(112), Integer.valueOf(116), Integer.valueOf(120), Integer.valueOf(124),
            Integer.valueOf(127), Integer.valueOf(131), Integer.valueOf(135), Integer.valueOf(139), Integer.valueOf(143), Integer.valueOf(146), Integer.valueOf(150), Integer.valueOf(154), Integer.valueOf(157), Integer.valueOf(161),
            Integer.valueOf(164), Integer.valueOf(167), Integer.valueOf(171), Integer.valueOf(174), Integer.valueOf(177), Integer.valueOf(181), Integer.valueOf(184), Integer.valueOf(187), Integer.valueOf(190), Integer.valueOf(193),
            Integer.valueOf(196), Integer.valueOf(198), Integer.valueOf(201), Integer.valueOf(204), Integer.valueOf(207), Integer.valueOf(209), Integer.valueOf(212), Integer.valueOf(214), Integer.valueOf(217), Integer.valueOf(219),
            Integer.valueOf(221), Integer.valueOf(223), Integer.valueOf(226), Integer.valueOf(228), Integer.valueOf(230), Integer.valueOf(232), Integer.valueOf(233), Integer.valueOf(235), Integer.valueOf(237), Integer.valueOf(238),
            Integer.valueOf(240), Integer.valueOf(242), Integer.valueOf(243), Integer.valueOf(244), Integer.valueOf(246), Integer.valueOf(247), Integer.valueOf(248), Integer.valueOf(249), Integer.valueOf(250), Integer.valueOf(251),
            Integer.valueOf(252), Integer.valueOf(252), Integer.valueOf(253), Integer.valueOf(254), Integer.valueOf(254), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255),
            Integer.valueOf(256), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(254), Integer.valueOf(254), Integer.valueOf(253), Integer.valueOf(252),
            Integer.valueOf(252), Integer.valueOf(251), Integer.valueOf(250), Integer.valueOf(249), Integer.valueOf(248), Integer.valueOf(247), Integer.valueOf(246), Integer.valueOf(244), Integer.valueOf(243), Integer.valueOf(242),
            Integer.valueOf(240), Integer.valueOf(238), Integer.valueOf(237), Integer.valueOf(235), Integer.valueOf(233), Integer.valueOf(232), Integer.valueOf(230), Integer.valueOf(228), Integer.valueOf(226), Integer.valueOf(223),
            Integer.valueOf(221), Integer.valueOf(219), Integer.valueOf(217), Integer.valueOf(214), Integer.valueOf(212), Integer.valueOf(209), Integer.valueOf(207), Integer.valueOf(204), Integer.valueOf(201), Integer.valueOf(198),
            Integer.valueOf(196), Integer.valueOf(193), Integer.valueOf(190), Integer.valueOf(187), Integer.valueOf(184), Integer.valueOf(181), Integer.valueOf(177), Integer.valueOf(174), Integer.valueOf(171), Integer.valueOf(167),
            Integer.valueOf(164), Integer.valueOf(161), Integer.valueOf(157), Integer.valueOf(154), Integer.valueOf(150), Integer.valueOf(146), Integer.valueOf(143), Integer.valueOf(139), Integer.valueOf(135), Integer.valueOf(131),
            Integer.valueOf(127), Integer.valueOf(124), Integer.valueOf(120), Integer.valueOf(116), Integer.valueOf(112), Integer.valueOf(108), Integer.valueOf(104), Integer.valueOf(100), Integer.valueOf(95), Integer.valueOf(91),
            Integer.valueOf(87), Integer.valueOf(83), Integer.valueOf(79), Integer.valueOf(74), Integer.valueOf(70), Integer.valueOf(66), Integer.valueOf(61), Integer.valueOf(57), Integer.valueOf(53), Integer.valueOf(48),
            Integer.valueOf(44), Integer.valueOf(40), Integer.valueOf(35), Integer.valueOf(31), Integer.valueOf(26), Integer.valueOf(22), Integer.valueOf(17), Integer.valueOf(13), Integer.valueOf(8), Integer.valueOf(4),
            Integer.valueOf(0), Integer.valueOf(-4), Integer.valueOf(-8), Integer.valueOf(-13), Integer.valueOf(-17), Integer.valueOf(-22), Integer.valueOf(-26), Integer.valueOf(-31), Integer.valueOf(-35), Integer.valueOf(-40),
            Integer.valueOf(-44), Integer.valueOf(-48), Integer.valueOf(-53), Integer.valueOf(-57), Integer.valueOf(-61), Integer.valueOf(-66), Integer.valueOf(-70), Integer.valueOf(-74), Integer.valueOf(-79), Integer.valueOf(-83),
            Integer.valueOf(-87), Integer.valueOf(-91), Integer.valueOf(-95), Integer.valueOf(-100), Integer.valueOf(-104), Integer.valueOf(-108), Integer.valueOf(-112), Integer.valueOf(-116), Integer.valueOf(-120), Integer.valueOf(-124),
            Integer.valueOf(-128), Integer.valueOf(-131), Integer.valueOf(-135), Integer.valueOf(-139), Integer.valueOf(-143), Integer.valueOf(-146), Integer.valueOf(-150), Integer.valueOf(-154), Integer.valueOf(-157), Integer.valueOf(-161),
            Integer.valueOf(-164), Integer.valueOf(-167), Integer.valueOf(-171), Integer.valueOf(-174), Integer.valueOf(-177), Integer.valueOf(-181), Integer.valueOf(-184), Integer.valueOf(-187), Integer.valueOf(-190), Integer.valueOf(-193),
            Integer.valueOf(-196), Integer.valueOf(-198), Integer.valueOf(-201), Integer.valueOf(-204), Integer.valueOf(-207), Integer.valueOf(-209), Integer.valueOf(-212), Integer.valueOf(-214), Integer.valueOf(-217), Integer.valueOf(-219),
            Integer.valueOf(-221), Integer.valueOf(-223), Integer.valueOf(-226), Integer.valueOf(-228), Integer.valueOf(-230), Integer.valueOf(-232), Integer.valueOf(-233), Integer.valueOf(-235), Integer.valueOf(-237), Integer.valueOf(-238),
            Integer.valueOf(-240), Integer.valueOf(-242), Integer.valueOf(-243), Integer.valueOf(-244), Integer.valueOf(-246), Integer.valueOf(-247), Integer.valueOf(-248), Integer.valueOf(-249), Integer.valueOf(-250), Integer.valueOf(-251),
            Integer.valueOf(-252), Integer.valueOf(-252), Integer.valueOf(-253), Integer.valueOf(-254), Integer.valueOf(-254), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255),
            Integer.valueOf(-256), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-254), Integer.valueOf(-254), Integer.valueOf(-253), Integer.valueOf(-252),
            Integer.valueOf(-252), Integer.valueOf(-251), Integer.valueOf(-250), Integer.valueOf(-249), Integer.valueOf(-248), Integer.valueOf(-247), Integer.valueOf(-246), Integer.valueOf(-244), Integer.valueOf(-243), Integer.valueOf(-242),
            Integer.valueOf(-240), Integer.valueOf(-238), Integer.valueOf(-237), Integer.valueOf(-235), Integer.valueOf(-233), Integer.valueOf(-232), Integer.valueOf(-230), Integer.valueOf(-228), Integer.valueOf(-226), Integer.valueOf(-223),
            Integer.valueOf(-221), Integer.valueOf(-219), Integer.valueOf(-217), Integer.valueOf(-214), Integer.valueOf(-212), Integer.valueOf(-209), Integer.valueOf(-207), Integer.valueOf(-204), Integer.valueOf(-201), Integer.valueOf(-198),
            Integer.valueOf(-196), Integer.valueOf(-193), Integer.valueOf(-190), Integer.valueOf(-187), Integer.valueOf(-184), Integer.valueOf(-181), Integer.valueOf(-177), Integer.valueOf(-174), Integer.valueOf(-171), Integer.valueOf(-167),
            Integer.valueOf(-164), Integer.valueOf(-161), Integer.valueOf(-157), Integer.valueOf(-154), Integer.valueOf(-150), Integer.valueOf(-146), Integer.valueOf(-143), Integer.valueOf(-139), Integer.valueOf(-135), Integer.valueOf(-131),
            Integer.valueOf(-128), Integer.valueOf(-124), Integer.valueOf(-120), Integer.valueOf(-116), Integer.valueOf(-112), Integer.valueOf(-108), Integer.valueOf(-104), Integer.valueOf(-100), Integer.valueOf(-95), Integer.valueOf(-91),
            Integer.valueOf(-87), Integer.valueOf(-83), Integer.valueOf(-79), Integer.valueOf(-74), Integer.valueOf(-70), Integer.valueOf(-66), Integer.valueOf(-61), Integer.valueOf(-57), Integer.valueOf(-53), Integer.valueOf(-48),
            Integer.valueOf(-44), Integer.valueOf(-40), Integer.valueOf(-35), Integer.valueOf(-31), Integer.valueOf(-26), Integer.valueOf(-22), Integer.valueOf(-17), Integer.valueOf(-13), Integer.valueOf(-8), Integer.valueOf(-4)};

    public static Integer[] m_ar_ar_iTblY = new Integer[]{
            Integer.valueOf(-256), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-254), Integer.valueOf(-254), Integer.valueOf(-253), Integer.valueOf(-252),
            Integer.valueOf(-252), Integer.valueOf(-251), Integer.valueOf(-250), Integer.valueOf(-249), Integer.valueOf(-248), Integer.valueOf(-247), Integer.valueOf(-246), Integer.valueOf(-244), Integer.valueOf(-243), Integer.valueOf(-242),
            Integer.valueOf(-240), Integer.valueOf(-238), Integer.valueOf(-237), Integer.valueOf(-235), Integer.valueOf(-233), Integer.valueOf(-232), Integer.valueOf(-230), Integer.valueOf(-228), Integer.valueOf(-226), Integer.valueOf(-223),
            Integer.valueOf(-221), Integer.valueOf(-219), Integer.valueOf(-217), Integer.valueOf(-214), Integer.valueOf(-212), Integer.valueOf(-209), Integer.valueOf(-207), Integer.valueOf(-204), Integer.valueOf(-201), Integer.valueOf(-198),
            Integer.valueOf(-196), Integer.valueOf(-193), Integer.valueOf(-190), Integer.valueOf(-187), Integer.valueOf(-184), Integer.valueOf(-181), Integer.valueOf(-177), Integer.valueOf(-174), Integer.valueOf(-171), Integer.valueOf(-167),
            Integer.valueOf(-164), Integer.valueOf(-161), Integer.valueOf(-157), Integer.valueOf(-154), Integer.valueOf(-150), Integer.valueOf(-146), Integer.valueOf(-143), Integer.valueOf(-139), Integer.valueOf(-135), Integer.valueOf(-131),
            Integer.valueOf(-128), Integer.valueOf(-124), Integer.valueOf(-120), Integer.valueOf(-116), Integer.valueOf(-112), Integer.valueOf(-108), Integer.valueOf(-104), Integer.valueOf(-100), Integer.valueOf(-95), Integer.valueOf(-91),
            Integer.valueOf(-87), Integer.valueOf(-83), Integer.valueOf(-79), Integer.valueOf(-74), Integer.valueOf(-70), Integer.valueOf(-66), Integer.valueOf(-61), Integer.valueOf(-57), Integer.valueOf(-53), Integer.valueOf(-48),
            Integer.valueOf(-44), Integer.valueOf(-40), Integer.valueOf(-35), Integer.valueOf(-31), Integer.valueOf(-26), Integer.valueOf(-22), Integer.valueOf(-17), Integer.valueOf(-13), Integer.valueOf(-8), Integer.valueOf(-4),
            Integer.valueOf(0), Integer.valueOf(4), Integer.valueOf(8), Integer.valueOf(13), Integer.valueOf(17), Integer.valueOf(22), Integer.valueOf(26), Integer.valueOf(31), Integer.valueOf(35), Integer.valueOf(40),
            Integer.valueOf(44), Integer.valueOf(48), Integer.valueOf(53), Integer.valueOf(57), Integer.valueOf(61), Integer.valueOf(66), Integer.valueOf(70), Integer.valueOf(74), Integer.valueOf(79), Integer.valueOf(83),
            Integer.valueOf(87), Integer.valueOf(91), Integer.valueOf(95), Integer.valueOf(100), Integer.valueOf(104), Integer.valueOf(108), Integer.valueOf(112), Integer.valueOf(116), Integer.valueOf(120), Integer.valueOf(124),
            Integer.valueOf(127), Integer.valueOf(131), Integer.valueOf(135), Integer.valueOf(139), Integer.valueOf(143), Integer.valueOf(146), Integer.valueOf(150), Integer.valueOf(154), Integer.valueOf(157), Integer.valueOf(161),
            Integer.valueOf(164), Integer.valueOf(167), Integer.valueOf(171), Integer.valueOf(174), Integer.valueOf(177), Integer.valueOf(181), Integer.valueOf(184), Integer.valueOf(187), Integer.valueOf(190), Integer.valueOf(193),
            Integer.valueOf(196), Integer.valueOf(198), Integer.valueOf(201), Integer.valueOf(204), Integer.valueOf(207), Integer.valueOf(209), Integer.valueOf(212), Integer.valueOf(214), Integer.valueOf(217), Integer.valueOf(219),
            Integer.valueOf(221), Integer.valueOf(223), Integer.valueOf(226), Integer.valueOf(228), Integer.valueOf(230), Integer.valueOf(232), Integer.valueOf(233), Integer.valueOf(235), Integer.valueOf(237), Integer.valueOf(238),
            Integer.valueOf(240), Integer.valueOf(242), Integer.valueOf(243), Integer.valueOf(244), Integer.valueOf(246), Integer.valueOf(247), Integer.valueOf(248), Integer.valueOf(249), Integer.valueOf(250), Integer.valueOf(251),
            Integer.valueOf(252), Integer.valueOf(252), Integer.valueOf(253), Integer.valueOf(254), Integer.valueOf(254), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255),
            Integer.valueOf(256), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(255), Integer.valueOf(254), Integer.valueOf(254), Integer.valueOf(253), Integer.valueOf(252),
            Integer.valueOf(252), Integer.valueOf(251), Integer.valueOf(250), Integer.valueOf(249), Integer.valueOf(248), Integer.valueOf(247), Integer.valueOf(246), Integer.valueOf(244), Integer.valueOf(243), Integer.valueOf(242),
            Integer.valueOf(240), Integer.valueOf(238), Integer.valueOf(237), Integer.valueOf(235), Integer.valueOf(233), Integer.valueOf(232), Integer.valueOf(230), Integer.valueOf(228), Integer.valueOf(226), Integer.valueOf(223),
            Integer.valueOf(221), Integer.valueOf(219), Integer.valueOf(217), Integer.valueOf(214), Integer.valueOf(212), Integer.valueOf(209), Integer.valueOf(207), Integer.valueOf(204), Integer.valueOf(201), Integer.valueOf(198),
            Integer.valueOf(196), Integer.valueOf(193), Integer.valueOf(190), Integer.valueOf(187), Integer.valueOf(184), Integer.valueOf(181), Integer.valueOf(177), Integer.valueOf(174), Integer.valueOf(171), Integer.valueOf(167),
            Integer.valueOf(164), Integer.valueOf(161), Integer.valueOf(157), Integer.valueOf(154), Integer.valueOf(150), Integer.valueOf(146), Integer.valueOf(143), Integer.valueOf(139), Integer.valueOf(135), Integer.valueOf(131),
            Integer.valueOf(128), Integer.valueOf(124), Integer.valueOf(120), Integer.valueOf(116), Integer.valueOf(112), Integer.valueOf(108), Integer.valueOf(104), Integer.valueOf(100), Integer.valueOf(95), Integer.valueOf(91),
            Integer.valueOf(87), Integer.valueOf(83), Integer.valueOf(79), Integer.valueOf(74), Integer.valueOf(70), Integer.valueOf(66), Integer.valueOf(61), Integer.valueOf(57), Integer.valueOf(53), Integer.valueOf(48),
            Integer.valueOf(44), Integer.valueOf(40), Integer.valueOf(35), Integer.valueOf(31), Integer.valueOf(26), Integer.valueOf(22), Integer.valueOf(17), Integer.valueOf(13), Integer.valueOf(8), Integer.valueOf(4),
            Integer.valueOf(0), Integer.valueOf(-4), Integer.valueOf(-8), Integer.valueOf(-13), Integer.valueOf(-17), Integer.valueOf(-22), Integer.valueOf(-26), Integer.valueOf(-31), Integer.valueOf(-35), Integer.valueOf(-40),
            Integer.valueOf(-44), Integer.valueOf(-48), Integer.valueOf(-53), Integer.valueOf(-57), Integer.valueOf(-61), Integer.valueOf(-66), Integer.valueOf(-70), Integer.valueOf(-74), Integer.valueOf(-79), Integer.valueOf(-83),
            Integer.valueOf(-87), Integer.valueOf(-91), Integer.valueOf(-95), Integer.valueOf(-100), Integer.valueOf(-104), Integer.valueOf(-108), Integer.valueOf(-112), Integer.valueOf(-116), Integer.valueOf(-120), Integer.valueOf(-124),
            Integer.valueOf(-128), Integer.valueOf(-131), Integer.valueOf(-135), Integer.valueOf(-139), Integer.valueOf(-143), Integer.valueOf(-146), Integer.valueOf(-150), Integer.valueOf(-154), Integer.valueOf(-157), Integer.valueOf(-161),
            Integer.valueOf(-164), Integer.valueOf(-167), Integer.valueOf(-171), Integer.valueOf(-174), Integer.valueOf(-177), Integer.valueOf(-181), Integer.valueOf(-184), Integer.valueOf(-187), Integer.valueOf(-190), Integer.valueOf(-193),
            Integer.valueOf(-196), Integer.valueOf(-198), Integer.valueOf(-201), Integer.valueOf(-204), Integer.valueOf(-207), Integer.valueOf(-209), Integer.valueOf(-212), Integer.valueOf(-214), Integer.valueOf(-217), Integer.valueOf(-219),
            Integer.valueOf(-221), Integer.valueOf(-223), Integer.valueOf(-226), Integer.valueOf(-228), Integer.valueOf(-230), Integer.valueOf(-232), Integer.valueOf(-233), Integer.valueOf(-235), Integer.valueOf(-237), Integer.valueOf(-238),
            Integer.valueOf(-240), Integer.valueOf(-242), Integer.valueOf(-243), Integer.valueOf(-244), Integer.valueOf(-246), Integer.valueOf(-247), Integer.valueOf(-248), Integer.valueOf(-249), Integer.valueOf(-250), Integer.valueOf(-251),
            Integer.valueOf(-252), Integer.valueOf(-252), Integer.valueOf(-253), Integer.valueOf(-254), Integer.valueOf(-254), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255), Integer.valueOf(-255)};

    public static int ValidateDirValue(int a_iDirValue) {
        if (a_iDirValue > 359)
            return a_iDirValue % 360;
        if (a_iDirValue < 0)
            return 360 + a_iDirValue % 360;
        return a_iDirValue;
    }

    public static int GetBaseVelX(int tValue) {
        return m_ar_ar_iTblX[ValidateDirValue(tValue)].intValue();
    }

    public static int GetBaseVelY(int tValue) {
        return m_ar_ar_iTblY[ValidateDirValue(tValue)].intValue();
    }

    public static int TileToWorld(int tNum) {
        return tNum * 3200;
    }

    public static int WorldToTile(int tNum) {
        return (tNum + 1600) / 3200;
    }

    public static int bitXor(int x, int y) {
        return x ^ y;
    }

    public static int bitNot(int x) {
        return x ^ 0xFFFFFFFF;
    }

    public static int bitAnd(int x, int y) {
        return x & y;
    }

    public static int bitOr(int x, int y) {
        return x | y;
    }

    public static int IterateSeed(int a_iSeed) {
        int t_iSeed2 = 0;
        if (a_iSeed == 0)
            a_iSeed = -1;
        t_iSeed2 = BitLeft(a_iSeed, 13);
        a_iSeed = bitXor(a_iSeed, t_iSeed2);
        t_iSeed2 = BitRight(a_iSeed, 17);
        a_iSeed = bitXor(a_iSeed, t_iSeed2);
        t_iSeed2 = BitLeft(a_iSeed, 5);
        a_iSeed = bitXor(a_iSeed, t_iSeed2);
        return a_iSeed;
    }

    public static int BitRight(int n, int s) {
        return n >> s;
    }

    public static int BitLeft(int n, int s) {
        return n << s;
    }

    public static Integer[] calculateFlightPath(int UserX, int UserY, int TargetX, int TargetY, int Trajetory) {
        Integer[] outPut = {Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0)};
        int tX = TileToWorld(UserX);
        int tY = TileToWorld(UserY);
        int tarX = TileToWorld(TargetX);
        int tarY = TileToWorld(TargetY);
        int tDeltaX = (tarX - tX) / 200;
        int tDeltaY = (tarY - tY) / 200;
        outPut[0] = Integer.valueOf(getAngleFromComponents(tDeltaX, tDeltaY));
        if (Trajetory == 1) {
            outPut[1] = Integer.valueOf(13);
        } else if (Trajetory == 2) {
            int tDistanceToTarget = fast_sqrt(tDeltaX * tDeltaX + tDeltaY * tDeltaY) * 200;
            outPut[1] = Integer.valueOf(tDistanceToTarget / 2000);
        } else {
            int tDistanceToTarget = fast_sqrt(tDeltaX * tDeltaX + tDeltaY * tDeltaY) * 200;
            outPut[1] = Integer.valueOf(tDistanceToTarget / 2000);
        }
        outPut[2] = Integer.valueOf(outPut[1].intValue() / 2);
        return outPut;
    }

    /*
    public boolean TestCircleToCircleCollision(GamePlayer tThisObject, SnowballObject tOtherObject) {
        int distanceX = tOtherObject.getLocH() - TileToWorld(tThisObject.getSnowStormAttributes().getCurrentPosition().getX());
        if (distanceX < 0)
            distanceX = -distanceX;
        int distanceY = tOtherObject.getLocV() - TileToWorld(tThisObject.getSnowStormAttributes().getCurrentPosition().getY());
        if (distanceY < 0)
            distanceY = -distanceY;
        int collisionDistance = 2000;
        if (distanceY < collisionDistance && distanceX < collisionDistance &&
                distanceX * distanceX + distanceY * distanceY < collisionDistance * collisionDistance)
            return true;
        return false;
    }

     */

    public static Position getTileNeighborInDirection (int tX, int tY, int tdir) {
        return switch (tdir) {
            case 0 -> new Position(tX, tY - 1);
            case 1 -> new Position(tX + 1, tY - 1);
            case 2 -> new Position(tX + 1, tY);
            case 3 -> new Position(tX + 1, tY + 1);
            case 4 -> new Position(tX, tY + 1);
            case 5 -> new Position(tX - 1, tY + 1);
            case 6 -> new Position(tX - 1, tY);
            case 7 -> new Position(tX - 1, tY - 1);
            default -> null;
        };
    }
  /*case tdir of
    0:
            return me.getTile(tX, tY - 1)
            1:
            return me.getTile(tX + 1, tY - 1)
            2:
            return me.getTile(tX + 1, tY)
            3:
            return me.getTile(tX + 1, tY + 1)
            4:
            return me.getTile(tX, tY + 1)
            5:
            return me.getTile(tX - 1, tY + 1)
            6:
            return me.getTile(tX - 1, tY)
            7:
            return me.getTile(tX - 1, tY - 1)
    otherwise:
            return error(me, "Invalid direction for tile:" && tdir, #getTileNeighborInDirection)
    end case*/

    //end

    public static Integer[] convertWorldToScreenCoordinate(int tX, int tY, int tZ) {
        int pAccuracyFactor = 100;
        int pTileWidth = 32;
        int tMultiplier = pTileWidth * pAccuracyFactor;
        tX = 0 + tX / tMultiplier;
        tY = 0 + tY / tMultiplier;
        tZ /= tMultiplier;
        Integer[] tloc = getScreenCoordinate(tX, tY, tZ);
        return tloc;
    }

    public static Integer[] getScreenCoordinate(int tX, int tY, int tHight) {
        Integer[] tLoc = {Integer.valueOf(0), Integer.valueOf(0)};
        int tLocH = 336 + tX * 16 - tY * 16;
        int tLocV = -151 + tX * 8 + tY * 8 - tHight * 16;
        tLoc[0] = Integer.valueOf(tLocH);
        tLoc[1] = Integer.valueOf(tLocV);
        return tLoc;
    }

    public static int convertToWorldCoordinate(int num) {
        int pAccuracyFactor = 100;
        int pTileSize = 32;
        int tMultiplier = pTileSize * pAccuracyFactor;
        return num * tMultiplier;
    }
}