package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        // O(n) - один цикл с n итерациями
        // R(1) - используемая память не зависит от n
        int resultRight = 0;
        int resultLeft = 0;
        int left = 0;
        int max = 0;
        int sum = 0;
        int i = 2;
        try (BufferedReader br = new BufferedReader(new FileReader(inputName))) {
            int prev = Integer.parseInt(br.readLine());
            String line = br.readLine();
            while (line != null) {
                int num = Integer.parseInt(line);

                sum += num - prev;
                if (sum > max) {
                    max = sum;
                    resultLeft = left;
                    resultRight = i;
                }
                if (sum < 0) {
                    sum = 0;
                    left = i;
                }

                line = br.readLine();
                prev = num;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Pair<>(resultLeft, resultRight);
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String firs, String second) {
        throw new NotImplementedError();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        // Используется решето Аткина
        // O(n) - два цикл с n итерациями и два цикла с sqrt(n) инерациями
        // R(n) - один массив размера n + 1
        if (limit > 1) {
            int sqr_lim = (int) Math.sqrt(limit);
            boolean[] is_prime = new boolean[limit + 1];

            for (int i = 0; i <= limit; ++i) {
                is_prime[i] = i == 2 || i == 3;
            }

            int y2; // x2 и y2 — это квадраты i и j
            int x2 = 0;
            int n;
            for (int i = 1; i <= sqr_lim; ++i) {
                x2 += 2 * i - 1;
                y2 = 0;
                for (int j = 1; j <= sqr_lim; ++j) {
                    y2 += 2 * j - 1;

                    n = 4 * x2 + y2;
                    if ((n <= limit) && (n % 12 == 1 || n % 12 == 5))
                        is_prime[n] = !is_prime[n];

                    n -= x2; // n = 3 * x2 + y2
                    if ((n <= limit) && (n % 12 == 7))
                        is_prime[n] = !is_prime[n];

                    n -= 2 * y2; // n = 3 * x2 - y2
                    if ((i > j) && (n <= limit) && (n % 12 == 11))
                        is_prime[n] = !is_prime[n];
                }
            }

            // Отсеиваем кратные квадратам простых чисел в интервале [5, sqr_lim].
            for (int i = 5; i <= sqr_lim; ++i) {
                if (is_prime[i]) {
                    int sqr = i * i;
                    for (int j = sqr; j <= limit; j += sqr)
                        is_prime[j] = false;
                }
            }
            int result = 0;
            for (boolean prime : is_prime) { // Считаем количество простых чисел в массиве
                if (prime) result++;
            }
            return result;
        } else
            return 0;
    }
}
