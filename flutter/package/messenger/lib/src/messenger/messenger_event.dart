import 'package:equatable/equatable.dart';

sealed class StorageUpdateEvent extends Equatable {
  const StorageUpdateEvent(this.data);

  final int data;

  @override
  List<Object?> get props => [data];
}

final class StorageUpdateRequest extends StorageUpdateEvent {
  const StorageUpdateRequest(super.data);
}

final class StorageUpdateResult extends StorageUpdateEvent {
  const StorageUpdateResult(super.data);
}
