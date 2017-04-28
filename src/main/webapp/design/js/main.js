function getRow(name, band, year, rating, id) {
    return '<tr>' +
        '<td>' + name + '</td>' +
    '<td>' + band + '</td>' +
    '<td>' + year + '</td>' +
    '<td>' + rating + '</td>' +
    '<td><a href="/main?action=delete&id=' + id + '" style="color:black; text-decoration: none">Remove</a></td>' +
    '</tr>';
}

function showAlbum(albums) {
    var list = '';

    for (var i = 0; i < albums.length; i++) {
        var albumEntry = albums[i];
        list += getRow(albumEntry.name, albumEntry.band, albumEntry.year, albumEntry.rating, albumEntry.id);
    }

    document.getElementsByTagName('tbody')[0].innerHTML = list;
}

$.ajax('/main?action=list').then(function(albums){
    showAlbum(albums);
});





