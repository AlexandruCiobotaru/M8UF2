# M8UF2

  This aplication is the client version of PCShop, for users.
  
  It consists off a app that allows you to search for diferent pcs, laptops and computer components.
  You can order them and if the admin at the server version of the app accepts it will appear on you Orders list.
  
  LOGIN:
    To make the login we used google autenthication from Firebase, so you can log in whit your google account.
     
  HOME:
  For home's view we used a side bar menu whit 5 diferent options on one hand: Computers, Laptops, Keyboards, Screens and Mouses.
  And for the other hand a "Orders" option so you can see the orders that the admin whit the server version of the app accepted.
  
  For bohot options we used Fragments: ProductFragmentList and OrdersListFragment that makes use of a RecyclerViewAdapter to show the products.
  Every product here have a name and an image that have their blueprint at item_list.xml
  
  For every product we allowed them so they can be touched to go to the ProductFragment (the detailed version for every product). 
  On this view appears tehir name, price, image and description. Also here you have a button to order it.
  
  
  Products:
  All the products have these atributes: name, price, description, image(URL), accepted(bool) and ordered(bool).
  
  Images:
  We used Glide so we can take the url of our images from the storage on Firebase.
  Every Object on Firebase has its onw property: image. 
  It's a string that have the url of the image on the Firebase storage.
