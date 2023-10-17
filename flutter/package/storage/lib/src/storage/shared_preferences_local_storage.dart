import 'dart:async';

import 'package:injectable/injectable.dart';
import 'package:messenger/messenger.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'local_storage.dart';

@Singleton(as: LocalStorage)
class SharedPreferencesLocalStorage implements LocalStorage {
  final SharedPreferences sharedPreferences;
  final MessengerBus messengerBus;
  StreamSubscription? _requestSubscription;

  SharedPreferencesLocalStorage(
    @Named('storage') this.messengerBus,
    this.sharedPreferences,
  ) {
    messengerBus.fire(StorageUpdateResult(getInt()));
    messengerBus.stream.listen((event) {
      if (event is StorageUpdateRequest) {
        setInt(event.data).then((success) {
          if (success) messengerBus.fire(StorageUpdateResult(event.data));
        });
      }
    });
  }

  @override
  Future<bool> setInt(int value) async {
    return sharedPreferences.setInt('saved_integer_key', value);
  }

  @override
  int getInt() {
    return sharedPreferences.getInt('saved_integer_key') ?? 0;
  }

  void dispose() {
    _requestSubscription?.cancel();
  }
}
