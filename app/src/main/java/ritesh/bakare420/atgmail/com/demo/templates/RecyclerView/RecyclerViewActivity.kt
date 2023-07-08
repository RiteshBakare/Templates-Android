package ritesh.bakare420.atgmail.com.demo.templates.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ritesh.bakare420.atgmail.com.demo.templates.R
import ritesh.bakare420.atgmail.com.demo.templates.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding


    private lateinit var data: ArrayList<Food>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.myRecyclerView.layoutManager = LinearLayoutManager(this)

        data = getData()

        val myRVAdapter = MyRVAdapter(data)
        binding.myRecyclerView.adapter = myRVAdapter
        myRVAdapter.setOnItemClickListener(object : MyRVAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(applicationContext, "Food name: ${data[position].name} and position: $position", Toast.LENGTH_LONG).show()
            }
        })

    }

    // function to get Data which is to be set into Recycler Views
    private fun getData(): ArrayList<Food> {

        val foodList = ArrayList<Food>()

        foodList.add(Food("Coffee", R.drawable.coffe))
        foodList.add(Food("Burger", R.drawable.burger))
        foodList.add(Food("Fries", R.drawable.fries))
        foodList.add(Food("pasta", R.drawable.pasta))
        foodList.add(Food("pizza", R.drawable.pizza))
        foodList.add(Food("Sandwich", R.drawable.sandwithc))
        foodList.add(Food("Coffee", R.drawable.coffe))
        foodList.add(Food("Burger", R.drawable.burger))
        foodList.add(Food("Fries", R.drawable.fries))
        foodList.add(Food("pasta", R.drawable.pasta))
        foodList.add(Food("pizza", R.drawable.pizza))
        foodList.add(Food("Sandwich", R.drawable.sandwithc))
        foodList.add(Food("Coffee", R.drawable.coffe))
        foodList.add(Food("Burger", R.drawable.burger))
        foodList.add(Food("Fries", R.drawable.fries))
        foodList.add(Food("pasta", R.drawable.pasta))
        foodList.add(Food("pizza", R.drawable.pizza))
        foodList.add(Food("Sandwich", R.drawable.sandwithc))
        foodList.add(Food("Coffee", R.drawable.coffe))
        foodList.add(Food("Burger", R.drawable.burger))
        foodList.add(Food("Fries", R.drawable.fries))
        foodList.add(Food("pasta", R.drawable.pasta))
        foodList.add(Food("pizza", R.drawable.pizza))
        foodList.add(Food("Sandwich", R.drawable.sandwithc))
        foodList.add(Food("Coffee", R.drawable.coffe))
        foodList.add(Food("Burger", R.drawable.burger))
        foodList.add(Food("Fries", R.drawable.fries))
        foodList.add(Food("pasta", R.drawable.pasta))
        foodList.add(Food("pizza", R.drawable.pizza))
        foodList.add(Food("Sandwich", R.drawable.sandwithc))

        return foodList
    }


    data class Food(
            val name: String,
            val image: Int,
    )


}