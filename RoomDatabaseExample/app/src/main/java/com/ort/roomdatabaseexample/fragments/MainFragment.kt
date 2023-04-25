package com.ort.roomdatabaseexample.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.ort.roomdatabaseexample.R
import com.ort.roomdatabaseexample.database.AppDatabase
import com.ort.roomdatabaseexample.database.UserDao
import com.ort.roomdatabaseexample.entities.User
import com.wajahatkarim3.roomexplorer.RoomExplorer


class MainFragment : Fragment() {

    private lateinit var v: View

    private var db: AppDatabase? = null
    private var userDao: UserDao? = null

    private lateinit var edtName: EditText
    private lateinit var edtEmail: EditText

    private lateinit var btnAdd: Button
    private lateinit var btnDelete: Button
    private lateinit var btnEdit: Button
    private lateinit var btnSearch: Button
    private lateinit var btnDebug: Button
    private lateinit var userList: MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        v = inflater.inflate(R.layout.fragment_main, container, false)

        edtName = v.findViewById(R.id.edt_name)
        edtEmail = v.findViewById(R.id.edt_email)

        btnAdd = v.findViewById(R.id.btn_add)
        btnDelete = v.findViewById(R.id.btn_delete)
        btnEdit = v.findViewById(R.id.btn_editar)
        btnSearch = v.findViewById(R.id.btn_search)
        btnDebug = v.findViewById(R.id.btn_debug)

        return v
    }

    override fun onStart() {
        super.onStart()

        db = AppDatabase.getInstance(v.context)
        userDao = db?.userDao()

        btnAdd.setOnClickListener {
            userDao?.insertPerson(User(0, edtName.text.toString(), edtEmail.text.toString()))
        }

        btnDelete.setOnClickListener {
            userDao?.delete(User(0, "", ""))
        }

        btnEdit.setOnClickListener {
            userDao?.updatePerson(User(0, "Juan", "juan@utn.com"))
        }

        btnSearch.setOnClickListener {
            Log.d("Test", userDao?.loadPersonById(0)?.name.toString())

            userList = userDao?.loadAllPersons() as MutableList<User>

            for (actualUser in userList) {
                Log.d("Test", actualUser.name)
            }
        }

        btnDebug.setOnClickListener {
            RoomExplorer.show(context, AppDatabase::class.java, "myDB")
        }
    }

}
