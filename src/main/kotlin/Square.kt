class Square(property: Double): Figure(property) {
    fun getPerimeter(): Double {
        return 4 * property
    }
    fun getArea(): Double {
        return property * property
    }

}