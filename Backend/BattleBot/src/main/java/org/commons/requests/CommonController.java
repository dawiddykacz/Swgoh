package org.commons.requests;

import org.commons.requests.dto.ResultDTO;
import org.commons.requests.dto.StatusRequestDto;
import org.commons.requests.dto.UUIDRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public class CommonController<T,U> {
    private final AppCommonQueueService<T,U> appCommonQueueService;

    public CommonController(AppCommonQueueService<T,U> appCommonQueueService) {
        this.appCommonQueueService = appCommonQueueService;
    }

    @GetMapping
    public ResponseEntity<StatusRequestDto> getUUID(@RequestParam String uuid) {
        return ResponseEntity.ok(new StatusRequestDto(
                this.appCommonQueueService.getStatus(uuid)));
    }

    @GetMapping("/result")
    public ResponseEntity<ResultDTO<T>> getStatus(@RequestBody UUIDRequestDto requestDto) {
        final T result = this.appCommonQueueService.getResult(requestDto.getUuid());
        if(result == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new ResultDTO<>(result));
    }

    @PostMapping
    public ResponseEntity<UUIDRequestDto> post(@RequestBody U dto) {
        final String uuid = this.appCommonQueueService.add(dto);
        return ResponseEntity.ok(new UUIDRequestDto(uuid));
    }
}
