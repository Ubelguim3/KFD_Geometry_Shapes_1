object FigureServiceImpl : FigureService {
    private var figureNames = mutableListOf<String>()
    private val circleList = mutableListOf<Circle>()
    private val squareList = mutableListOf<Square>()

    override fun addSquare(property: Double) {
        if (property <= 0 || property.isNaN()) {
            throw BadPropertyException("Введено неверное значение параметра property: $property ")
        }
        squareList.add(Square(property))
        println("Вы ввели Square, с длиной стороны: $property")
    }

    override fun addCircle(property: Double) {
        if (property <= 0 || property.isNaN()) {
            throw BadPropertyException("Введено неверное значение параметра property: $property ")
        }
        circleList.add(Circle(property))
        println("Вы ввели Circle, с длиной радиуса: $property")
    }

    override fun getPerimeter(): List<Double> {
        val perimeterList = mutableListOf<Double>()
        for (i in squareList) {
            perimeterList.add(i.getPerimeter())
        }
        for (i in circleList) {
            perimeterList.add(i.getPerimeter())
        }
        return perimeterList
    }

    override fun getArea(): List<Double> {
        val areaList = mutableListOf<Double>()

        for (i in squareList) {
            areaList.add(i.getArea())
        }
        for (i in circleList) {
            areaList.add(i.getArea())
        }
        return areaList
    }

    object ConsoleServiceImpl : ConsoleService {
        private fun addFigure() {
            println("Выберите необходимую фигуру: Circle или Square")
            val operation = readln()
            when (operation) {
                "Square" -> println("Введите длину стороны для Square")
                "Circle" -> println("Введите длину радиуса для Circle")
                else -> throw WrongFigureTypeException("Можно ввести только фигуры типа Square или Circle\n")
            }
            val property = readln().toDouble()
//            if (property <= 0 || property.isNaN()) {
//                throw BadPropertyException("Введённое значение должно быть больше 0\n")
//            }
            when (operation) {
                "Square" -> {
                    addSquare(property)
                    figureNames.add("Square")
                }
                "Circle" -> {
                    addCircle(property)
                    figureNames.add("Circle")
                }
            }


        }

        private fun getArea() {
            println("Площадь каждой фигуры: \n ${FigureServiceImpl.getArea()}")
            println(figureNames)
        }

        private fun getPerimeter() {
            println("Периметр каждой фигуры: \n ${FigureServiceImpl.getPerimeter()}")
            println(figureNames)
        }

        private fun getOperations(commandOperation: String): Operation {
            when (commandOperation) {
                "1" -> return Operation.INSERT
                "2" -> return Operation.GET_AREA
                "3" -> return Operation.GET_PERIMETER
                "4" -> return Operation.EXIT
            }
            return (Operation.EXCEPTION)
        }


        override fun work() {

            while (true) {
                println("Введите тип операции, которую хотите исполнить: \n 1)Добавить фигуру \n 2)Найти площадь \n 3)Найти периметр \n 4)Выход")
                val operation = getOperations(readln())
                try {
                    when (operation) {
                        Operation.INSERT -> {
                            try {
                                addFigure()
                            } catch (e: BadPropertyException) {
                                println("Введите положительное число для параметра Property ")
                            } catch (e: WrongFigureTypeException) {
                                println("Можно ввести только фигуры типа Circle или Square")
                            }
                        }

                        Operation.GET_AREA -> getArea()
                        Operation.GET_PERIMETER -> getPerimeter()
                        Operation.EXIT -> break
                        Operation.EXCEPTION -> throw WrongOperationTypeException("Введено неверное значение операции")
                    }
                } catch (e: WrongOperationTypeException) {
                    println("Введено неверное значение операции")

                }
            }
        }

    }

}


