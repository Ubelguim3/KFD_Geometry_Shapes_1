class Circle(property: Double) : Figure(property) {

    fun getPerimeter(): Double {
        return 2 * Math.PI * property
    }

    fun getArea(): Double {
        return Math.PI * property * property
    }
}