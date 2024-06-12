package com.example.mycity.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.ui.graphics.vector.ImageVector

public val Icons.Filled.Gallery: ImageVector
    get() {
        if (_gallery != null) {
            return _gallery!!
        }
        _gallery = materialIcon(name = "Filled.Gallery") {
            materialPath {
                moveTo(21.0f, 19.0f)
                horizontalLineTo(3.0f)
                verticalLineTo(5.0f)
                horizontalLineToRelative(18.0f)
                verticalLineToRelative(14.0f)
                close()
                moveTo(21.0f, 3.0f)
                horizontalLineTo(3.0f)
                curveTo(1.9f, 3.0f, 1.0f, 3.9f, 1.0f, 5.0f)
                verticalLineToRelative(14.0f)
                curveTo(1.0f, 20.1f, 1.9f, 21.0f, 3.0f, 21.0f)
                horizontalLineToRelative(18.0f)
                curveTo(22.1f, 21.0f, 23.0f, 20.1f, 23.0f, 19.0f)
                verticalLineTo(5.0f)
                curveTo(23.0f, 3.9f, 22.1f, 3.0f, 21.0f, 3.0f)
                close()
                moveTo(11.0f, 10.0f)
                lineToRelative(-1.41f, 1.41f)
                lineToRelative(-1.59f, -1.59f)
                lineTo(6.0f, 12.0f)
                lineToRelative(2.0f, 2.0f)
                lineToRelative(4.0f, -4.0f)
                lineToRelative(-1.0f, -1.0f)
                close()
            }
        }
        return _gallery!!
    }

private var _gallery: ImageVector? = null

public val Icons.Filled.ContactMail: ImageVector
    get() {
        if (_contactMail != null) {
            return _contactMail!!
        }
        _contactMail = materialIcon(name = "Filled.ContactMail") {
            materialPath {
                moveTo(21.0f, 8.0f)
                verticalLineToRelative(8.0f)
                horizontalLineToRelative(-2.0f)
                verticalLineTo(10.0f)
                horizontalLineTo(5.0f)
                verticalLineToRelative(6.0f)
                horizontalLineTo(3.0f)
                verticalLineTo(8.0f)
                horizontalLineToRelative(18.0f)
                close()
                moveTo(3.0f, 20.0f)
                horizontalLineToRelative(18.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineTo(3.0f)
                verticalLineTo(20.0f)
                close()
                moveTo(21.0f, 4.0f)
                horizontalLineToRelative(-18.0f)
                verticalLineTo(2.0f)
                horizontalLineToRelative(18.0f)
                verticalLineToRelative(2.0f)
                close()
                moveTo(12.0f, 11.0f)
                curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f)
                reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f)
                reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f)
                reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f)
                close()
            }
        }
        return _contactMail!!
    }

private var _contactMail: ImageVector? = null
