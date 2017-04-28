Albumselections

  This is an application that allows the user to keep track of the albums he bought and evaluate them by giving each one
of them a rating.
  The application can list, add and delete data from a local database. The interface was built using HTML and CSS and 
the connections were made using Java Servlets and Javascript. The HTML file, that contains a form in which the user 
will introduce specific data, is linked with Javascript which provides a function that retrieves rows, a function that displays the rows
and an AJAX call. The Javascript file is linked with the Servlet which provides 3 if-else statements that will call the listAction, 
addAction and deleteAction methods, depending on the value of the action(LIST_ACTION, add or delete). 
Within these 3 methods, the readAlbums, deleteAlbum and writeAlbums are called, which will execute queries and modify the table 
within the database.
    