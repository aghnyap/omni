import 'package:injectable/injectable.dart';
import 'package:messenger/messenger.dart';
import 'package:shared_preferences/shared_preferences.dart';

@module
abstract class RegisterModule {
  @preResolve
  Future<SharedPreferences> get prefs => SharedPreferences.getInstance();

  @Named('storage')
  MessengerBus get messengerBus => getIt<MessengerBus>();
}
