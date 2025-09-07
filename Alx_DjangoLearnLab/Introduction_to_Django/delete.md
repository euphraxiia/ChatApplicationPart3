```python
book = Book.objects.get(title="Nineteen Eighty-Four")
book.delete()
remaining_books = Book.objects.all()
print(f"Books count after deletion: {remaining_books.count()}")
Output:
(1, {'bookshelf.Book': 1})
Books count after deletion: 0
