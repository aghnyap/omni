import 'package:counter/counter.dart';
import 'package:flutter/material.dart';
import 'package:messenger/messenger.dart';
import 'package:storage/storage.dart';

import 'src/host_app.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  configureMessengerDependencies();
  configureCounterDependencies();
  configureStorageDependencies();
  runApp(const HostApp());
}
