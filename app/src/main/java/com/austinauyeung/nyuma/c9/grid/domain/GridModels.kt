package com.austinauyeung.nyuma.c9.grid.domain

import com.austinauyeung.nyuma.c9.core.constants.GridConstants
import com.austinauyeung.nyuma.c9.settings.domain.OverlaySettings

data class GridCell(
    val row: Int,
    val column: Int,
    val isSelected: Boolean = false,
    val number: Int = 0,
)

/**
 * Represents the entire grid and supports recursive subgrids.
 */
data class Grid(
    val cells: List<GridCell> = generateDefaultGrid(),
    val level: Int = 1,
    val maxLevels: Int = GridConstants.MIN_LEVELS,
    val parentCell: GridCell? = null,
    val isVisible: Boolean = false,
    val parentGrid: Grid? = null,
) {
    companion object {
        fun generateDefaultGrid(): List<GridCell> {
            val cells = mutableListOf<GridCell>()

            for (row in 0 until GridConstants.DIMENSION) {
                for (column in 0 until GridConstants.DIMENSION) {
                    cells.add(
                        GridCell(
                            row = row,
                            column = column,
                            number = GridConstants.INITIAL_NUMBERS[row][column],
                        ),
                    )
                }
            }
            return cells
        }

        fun createGrid(settings: OverlaySettings): Grid {
            return Grid(maxLevels = settings.gridLevels, isVisible = true)
        }
    }

    fun canCreateSubgrid(): Boolean {
        return level < maxLevels
    }
}
