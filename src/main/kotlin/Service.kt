interface ConsoleService {
    fun work()
}

interface FigureService {
    fun addSquare(property: Double)
    fun addCircle(property: Double)
    fun getPerimeter(): List<Double>
    fun getArea(): List<Double>
}
