package ca.ulaval.ima.tp2

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.util.*

fun Parcel.readCalendendar() : Calendar? {
    val msTime: Long = this.readLong()
    val result: Calendar = Calendar.getInstance()

    if (msTime != -1L) {
        result.timeInMillis = msTime
        return result
    }
    return null
}

fun Parcel.writeCalendar(calendar: Calendar?) {
    if (calendar != null) {
        val msTime: Long = calendar.timeInMillis
        this.writeLong(msTime)
    }
}

class Profile (
        var firstName: String?,
        var lastName: String?,
        var birthDate: Calendar?,
        var sexe: String?,
        var program: String?,
        var edited: Boolean?,
) : Parcelable {
    @RequiresApi(Build.VERSION_CODES.Q)
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readCalendendar(),
            parcel.readString(),
            parcel.readString(),
            parcel.readBoolean()) {
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeCalendar(birthDate)
        parcel.writeString(sexe)
        parcel.writeString(program)
        if (edited != null) {
            parcel.writeBoolean(edited!!)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        @RequiresApi(Build.VERSION_CODES.Q)
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }

}