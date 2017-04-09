package ru.job4j.paint;

/**
 * Class Triangle описывает способ изображения триугольника.
 */
public class Triangle implements Shape {
    /**
     * Формирует изображение ктриугольника.
     * @return изображение триугольника в формате строки
     */
    public String pic() {
        /*высота триуголтьника*/
        final int h = 3;
        /* Размер основания триугольника */
        int fund = 2 * h - 1;
		/* Рисунок триугольника */
        StringBuilder triangle = new StringBuilder();

        for (int i = 0; i < h; i++) {
			/*Формирование строки рисунка*/
            for (int j = 0; j < fund; j++) {
				/* Количество символов '*' в строке */
                int symbolNumber = 2 * i + 1;

				/* Позиция первго символа '*' в строке */
                int startSymbPos = (fund - symbolNumber) / 2;

				/* Позиция крайнего символа '*' в строке*/
                int endSymbPos = (fund + symbolNumber) / 2 - 1;

				/* Заполнение строки символами*/
                if ((startSymbPos <= j) && (j <= endSymbPos)) {
                    triangle.append('*');
                } else {
                    triangle.append(' ');
                }
            }
		    /* Перенос на новую строку и установка каретки в начало строки*/
            triangle.append("\r\n");
        }
        return triangle.toString();
    }

}
