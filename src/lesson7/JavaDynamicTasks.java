package lesson7;

import kotlin.NotImplementedError;

import java.io.*;
import java.util.*;

import static java.lang.Integer.min;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    public static String longestCommonSubSequence(String first, String second) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        // T = O(N^2) - два вложенных цикла дают O(N^2)
        // R = O(N) - используются списки или массивы ддлиной N
        if (list.size() < 2) return list;
        // d[i] - последовательность какой длины заканчивается элементом a[i]
        int[] d = new int[list.size()];
        // prev[i] - позиция предыдущего элемента в подпоследовательности для a[i]
        int[] prev = new int[list.size()];
        int maxLength = 1;
        for (int i = 0; i < list.size(); i++) {
            d[i] = 1;
            prev[i] = -1;
            for (int j = 0; j < i; j++) {
                if (list.get(j) <= list.get(i) && d[j] + 1 > d[i]) {
                    d[i] = d[j] + 1;
                    prev[i] = j;
                    if (d[i] > maxLength) maxLength = d[i];
                }
            }
        }
        // Нахождение всех подпоследовательностей с максимальной длиной
        List<Integer[]> subSequences = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (d[i] == maxLength) {
                subSequences.add(0, new Integer[maxLength]);
                subSequences.get(0)[maxLength - 1] = i;
                int prevIndex = prev[i];
                for (int j = maxLength - 2; j >= 0; j--) {
                    subSequences.get(0)[j] = prevIndex;
                    prevIndex = prev[prevIndex];
                }
            }
        }
        int resultIndex = 0;
        List<Integer> result = new ArrayList<>(maxLength);
        boolean isSame = true;
        for (int i = 0; i < maxLength; i++) {
            if (isSame) {
                for (int j = 1; j < subSequences.size(); j++) {
                    // Нахождение подпоследовательности, в которой числа расположены раньше всего
                    if (!subSequences.get(j)[i].equals(subSequences.get(0)[i])) {
                        if (subSequences.get(j)[i] < subSequences.get(0)[i]) resultIndex = j;
                        isSame = false;
                    }
                }
            }
            result.add(list.get(subSequences.get(resultIndex)[i]));
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) throws IOException {
        // N - количетво элементов в исходной таблице
        // T = O(N) - один раз заполнить таблицу из N элементов
        // R = O(N) - таблица из N элементов
        List<List<Integer>> field = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
            String line = br.readLine();
            while (line != null) {
                List<Integer> list = new ArrayList<>();
                for (String str : line.split(" ")) {
                    list.add(Integer.parseInt(str));
                }
                field.add(list);
                line = br.readLine();
            }
        }
        // Создаем новую таблицу, в которую будем записывать решение для подтаблиц
        Integer[][] minDist = new Integer[field.size()][field.get(0).size()];
        return shortestPath(minDist, field, 0, 0);
    }

    private static int shortestPath(Integer[][] paths, List<List<Integer>> field, final int i, final int j) {
        if (paths[i][j] != null) return paths[i][j];
        Integer n = field.get(i).get(j);
        if (i == paths.length - 1) {
            if (j == paths[0].length - 1) {
                paths[i][j] = n;
                return n;
            }
            paths[i][j] = n + shortestPath(paths, field, i, j + 1);
            return paths[i][j];
        }
        if (j == paths[0].length - 1) {
            paths[i][j] = n + shortestPath(paths, field, i + 1, j);
            return paths[i][j];
        }
        paths[i][j] = n + min(
                min(shortestPath(paths, field, i + 1, j), shortestPath(paths, field, i, j + 1)),
                shortestPath(paths, field, i + 1, j + 1)
        );
        return paths[i][j];
    }
}
