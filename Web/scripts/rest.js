function getGroups(callback) {
    $.ajax({
        url: "http://localhost:8080/groups"
    }).done(function (m) {
        callback(m)
    });
}

function getChildrenOfGroup(groupId, callback) {
    $.ajax({
        url: "http://localhost:8080/groups/" + groupId + "/children"
    }).done(function (m) {
        callback(m)
    });
}