import 'dart:async';

import 'package:bloc/bloc.dart';
import 'package:equatable/equatable.dart';
import 'package:injectable/injectable.dart';
import 'package:messenger/messenger.dart';

part 'counter_state.dart';

@injectable
class CounterCubit extends Cubit<CounterState> {
  final MessengerBus messengerBus;
  StreamSubscription? _messengerSubscription;

  CounterCubit(@Named('counter') this.messengerBus)
      : super(const CounterInitial()) {
    _messengerSubscription = messengerBus.stream.listen((event) {
      if (event is StorageUpdateResult) {
        emit(CounterInProgress(event.data));
      }
    });
  }

  void increment() {
    final count = state.count + 1;
    messengerBus.fire(StorageUpdateRequest(count));
  }

  void reset() {
    messengerBus.fire(const StorageUpdateRequest(0));
  }

  @override
  Future<void> close() {
    _messengerSubscription?.cancel();
    return super.close();
  }
}
