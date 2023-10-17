abstract interface class LocalStorage {
  Future<bool> setInt(int value);
  int getInt();
}
