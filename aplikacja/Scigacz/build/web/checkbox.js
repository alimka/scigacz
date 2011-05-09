function checkAll(doc) {
    if (doc.getElementById('CheckAll').checked) {
        for (i = 1; i <= 100; i++)
            doc.getElementById('cb'+i).checked = true;
    } else
        for (i = 1; i <= 100; i++)
            doc.getElementById('cb'+i).checked = false;
}