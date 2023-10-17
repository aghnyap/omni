import 'package:injectable/injectable.dart';
import 'package:messenger/messenger.dart';

@module
abstract class RegisterModule {
  @Named('counter')
  MessengerBus get messengerBus => getIt<MessengerBus>();
}
